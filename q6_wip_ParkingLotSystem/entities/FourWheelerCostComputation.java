package q6_wip_ParkingLotSystem.entities;

public class FourWheelerCostComputation extends CostComputation {
    public FourWheelerCostComputation() {
        super(new MinutePriceStrategy());
    }
}
