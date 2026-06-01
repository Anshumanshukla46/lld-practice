package q6_wip_ParkingLotSystem.entities;

import java.util.List;

public class DefaultParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot findSpace(List<ParkingSpot> spotList) {
        for(ParkingSpot slot: spotList){
            if(slot.isEmpty)
                return slot;
        }
        return null;
    }
}
