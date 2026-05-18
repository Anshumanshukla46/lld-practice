package q4_parkingLotSystem.src.entity;

public class VehicleInformation {
    private Vehicle vehicle;
    private int numberOfHours;
    private int paidfees;

    public VehicleInformation() {
    }

    public VehicleInformation(Vehicle vehicle, int numberOfHours, int paidfees) {
        this.vehicle = vehicle;
        this.numberOfHours = numberOfHours;
        this.paidfees = paidfees;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public int getPaidfees() {
        return paidfees;
    }

    public void setPaidfees(int paidfees) {
        this.paidfees = paidfees;
    }
}
