package q5_atmSystem.service;

import q5_atmSystem.entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ATMService {
    private ATM currAtm;
    private Map<String, AtmCard> atmCardMap;

    private TxnStatus authenticate(String cardNumber, String pin) {
        if (atmCardMap.containsKey(cardNumber)) {
            AtmCard card = atmCardMap.get(cardNumber);
            if (card.getCardNumber().equals(cardNumber) && card.getPin().equals(pin))
                return TxnStatus.SUCCESS;
        }
        return TxnStatus.INVALID_PIN;
    }

    public TxnStatus withDrawCash(String cardNumber, String pin, int amount) {
        if (authenticate(cardNumber, pin).equals(TxnStatus.INVALID_PIN))
            return TxnStatus.INVALID_PIN;

        try {
            AtmCard atmCard = atmCardMap.get(cardNumber);
            synchronized (ATM.class) {
                if (atmCard.getBalance() < amount)
                    return TxnStatus.INSUFFICIENT_BALANCE;
                if (currAtm.getTotalCash() < amount)
                    return TxnStatus.ATM_CASH_UNAVAILABLE;

                Transaction txn = new Transaction();
                txn.setTransactionId(UUID.randomUUID().toString());
                txn.setAmount(amount);
                txn.setTxnType(TxnType.WITHDRAW);
                txn.setTimestamp(LocalDateTime.now());

                List<Transaction> transactionList = atmCard.getTransactionList();

                TxnStatus status = TxnStatus.SUCCESS;
                txn.setTxnStatus(status);
                transactionList.add(txn);

                atmCard.setBalance(atmCard.getBalance()-amount);

                currAtm.setTotalCash(currAtm.getTotalCash() - amount);
                return status;
            }
        } catch (Exception e) {
            return TxnStatus.FAILED;
        }
    }

    public TxnStatus depositCash(String cardNumber, String pin, int amount) {
        if (authenticate(cardNumber, pin).equals(TxnStatus.INVALID_PIN))
            return TxnStatus.INVALID_PIN;

        try {
            AtmCard atmCard = atmCardMap.get(cardNumber);
            synchronized (ATM.class) {
                Transaction txn = new Transaction(UUID.randomUUID().toString(), amount, TxnType.DEPOSIT, TxnStatus.SUCCESS, LocalDateTime.now());
                List<Transaction> txns = atmCard.getTransactionList();
                txns.add(txn);
                atmCard.setBalance(atmCard.getBalance() + amount);
                currAtm.setTotalCash(currAtm.getTotalCash() + amount);

                return TxnStatus.SUCCESS;
            }
        } catch (Exception e) {
            return TxnStatus.FAILED;
        }
    }

    public TxnStatus checkBalance(String cardNumber, String pin) {
        if (authenticate(cardNumber, pin).equals(TxnStatus.INVALID_PIN))
            return TxnStatus.INVALID_PIN;

        try {
            AtmCard atmCard = atmCardMap.get(cardNumber);
            synchronized (ATM.class) {
                Transaction txn = new Transaction(UUID.randomUUID().toString(), atmCard.getBalance(), TxnType.BALANCE_CHECK, TxnStatus.SUCCESS, LocalDateTime.now());
                List<Transaction> txns = atmCard.getTransactionList();
                txns.add(txn);
                atmCard.setTransactionList(txns);
                return TxnStatus.SUCCESS;
            }
        } catch (Exception e) {
            return TxnStatus.FAILED;
        }
    }
}
