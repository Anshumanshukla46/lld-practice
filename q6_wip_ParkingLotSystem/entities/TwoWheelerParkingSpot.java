package q6_wip_ParkingLotSystem.entities;

public class TwoWheelerParkingSpot extends ParkingSpot {

    public TwoWheelerParkingSpot(int id) {
        this.id = id;
        this.isEmpty = true;
        this.vehicle = null;
        this.price = getPrice();
    }

    int getPrice() {
        return 10;
    }
}
