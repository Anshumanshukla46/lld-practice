package q6_wip_ParkingLotSystem.entities;

public class CostComputation {
    private final PriceStrategy priceStrategy;

    public CostComputation(PriceStrategy priceStrategy){
        this.priceStrategy = priceStrategy;
    }

    int getPrice(Ticket ticket) {
        return priceStrategy.getPrice(ticket);
    }
}
