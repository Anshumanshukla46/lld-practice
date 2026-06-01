package q6_wip_ParkingLotSystem.entities;

public class FourWheelerParkingSpot extends ParkingSpot {

    public FourWheelerParkingSpot(int id){
        this.id = id;
        this.isEmpty = true;
        this.vehicle = null;
        this.price = getPrice();
    }

    int getPrice() {
        return 20;
    }
}
