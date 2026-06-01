package q6_wip_ParkingLotSystem.entities;

import java.util.List;

public interface ParkingStrategy {
    ParkingSpot findSpace(List<ParkingSpot> spotList);
}
