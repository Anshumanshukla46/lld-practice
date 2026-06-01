package q5_atmSystem.entity;

public class ATM {
    private int totalCash;

    public ATM() {
    }

    public ATM(int totalCash) {
        this.totalCash = totalCash;
    }

    public int getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(int totalCash) {
        this.totalCash = totalCash;
    }
}
