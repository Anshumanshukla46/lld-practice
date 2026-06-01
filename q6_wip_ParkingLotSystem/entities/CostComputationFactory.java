package q6_wip_ParkingLotSystem.entities;

public class CostComputationFactory {

    CostComputation getCostComputationFactory(VehicleType vehicleType) {
        if (VehicleType.TWO_WHEELER.equals(vehicleType)) {
            return new TwoWheelerCostComputation();
        }
        return new FourWheelerCostComputation();
    }
}
