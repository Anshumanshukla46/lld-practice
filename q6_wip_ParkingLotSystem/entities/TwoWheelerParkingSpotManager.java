package q6_wip_ParkingLotSystem.entities;

import java.util.ArrayList;
import java.util.List;

public class TwoWheelerParkingSpotManager extends ParkingSpotManager {

    static List<ParkingSpot> spotList = new ArrayList<>();
    static ParkingStrategy parkingStrategy = new DefaultParkingStrategy();

    public TwoWheelerParkingSpotManager() {
        super(getTwoWheelerSpots(), parkingStrategy);
    }

    private static List<ParkingSpot> getTwoWheelerSpots() {
        for (int i = 1; i <= 600; i++) {
            spotList.add(new TwoWheelerParkingSpot(i));
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
