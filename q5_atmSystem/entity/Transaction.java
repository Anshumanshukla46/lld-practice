package q5_atmSystem.entity;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private int amount;
    private TxnType txnType;
    private TxnStatus txnStatus;
    private LocalDateTime timestamp;

    public Transaction(String transactionId, int amount, TxnType txnType, TxnStatus txnStatus, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.txnType = txnType;
        this.txnStatus = txnStatus;
        this.timestamp = timestamp;
    }

    public Transaction() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TxnType getTxnType() {
        return txnType;
    }

    public void setTxnType(TxnType txnType) {
        this.txnType = txnType;
    }

    public TxnStatus getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(TxnStatus txnStatus) {
        this.txnStatus = txnStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
