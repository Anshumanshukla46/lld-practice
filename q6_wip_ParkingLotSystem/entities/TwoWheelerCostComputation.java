package q6_wip_ParkingLotSystem.entities;

public class TwoWheelerCostComputation extends CostComputation {
    public TwoWheelerCostComputation() {
        super(new HourlyPriceStrategy());
    }
}
