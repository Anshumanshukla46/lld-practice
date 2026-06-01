package q6_wip_ParkingLotSystem.entities;

public class ParkingSpotFactory {

    ParkingSpotManager getParkingSpotManager(VehicleType vehicleType){
        if(VehicleType.TWO_WHEELER.equals(vehicleType))
            return new TwoWheelerParkingSpotManager();
        return new FourWheelerParkingSpotManager();
    }
}
