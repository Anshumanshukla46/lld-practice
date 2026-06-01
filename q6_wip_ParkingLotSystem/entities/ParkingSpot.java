package q6_wip_ParkingLotSystem.entities;

public abstract class ParkingSpot {
    int id;
    boolean isEmpty;
    int price;
    Vehicle vehicle;

    void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isEmpty = false;
    }

    void removeVehicle(Vehicle vehicle){
        this.vehicle =null;
        this.isEmpty = true;
    }
}
