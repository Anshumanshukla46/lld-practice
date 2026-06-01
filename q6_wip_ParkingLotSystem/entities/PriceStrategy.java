package q6_wip_ParkingLotSystem.entities;

public abstract class PriceStrategy {
    int getPrice(Ticket ticket){
        return ticket.parkingSpot.price;
    }
}
