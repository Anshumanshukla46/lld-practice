package q6_wip_ParkingLotSystem.entities;

import java.util.List;

public abstract class ParkingSpotManager {
    List<ParkingSpot> parkingSpotList;
    ParkingStrategy parkingStrategy;

    public ParkingSpotManager(List<ParkingSpot> list, ParkingStrategy parkingStrategy) {
        this.parkingSpotList = list;
        this.parkingStrategy = parkingStrategy;
    }

    public abstract ParkingSpot findParkingSpace();

    public abstract void parkVehicle(Vehicle vehicle);

    public abstract void removeVehicle(ParkingSpot parkingSpot);
}
