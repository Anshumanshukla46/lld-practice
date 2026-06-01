package q6_wip_ParkingLotSystem.entities;

public class MinutePriceStrategy extends PriceStrategy {
    @Override
    int getPrice(Ticket ticket) {
        return super.getPrice(ticket) * (int) ticket.entryTime;
    }
}
