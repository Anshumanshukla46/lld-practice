package q5_atmSystem.entity;

import java.util.List;

public class AtmCard {
    private String cardNumber;
    private String pin;
    private int balance;
    private List<Transaction> transactionList;

    public AtmCard() {
    }

    public AtmCard(String cardNumber, String pin, int balance, List<Transaction> transactionList) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionList = transactionList;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
