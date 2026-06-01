package q6_wip_ParkingLotSystem.entities;

import java.util.ArrayList;
import java.util.List;

public class FourWheelerParkingSpotManager extends ParkingSpotManager {

    static List<ParkingSpot> spotList = new ArrayList<>();
    static ParkingStrategy parkingStrategy = new NearToEntranceParkingStrategy();

    public FourWheelerParkingSpotManager() {
        super(getFourWheelerSpots(), parkingStrategy);
    }

    private static List<ParkingSpot> getFourWheelerSpots() {
        for (int i = 601; i <= 1000; i++) {
            spotList.add(new FourWheelerParkingSpot(i));
        }

        return spotList;
    }


    @Override
    public ParkingSpot findParkingSpace() {
        return parkingStrategy.findSpace(spotList);
    }

    @Override
    public void parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = findParkingSpace();
        parkingSpot.isEmpty = false;
        parkingSpot.vehicle = vehicle;
    }

    @Override
    public void removeVehicle(ParkingSpot parkingSpot) {
        parkingSpot.isEmpty = true;
        parkingSpot.vehicle = null;
    }
}
