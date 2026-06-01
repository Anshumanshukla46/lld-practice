package q6_wip_ParkingLotSystem.entities;

public class ExitGate {
    Ticket ticket;
    CostComputationFactory costComputationFactory;
    CostComputation costComputation;
    ParkingSpotFactory parkingSpotFactory;
    ParkingSpotManager parkingSpotManager;
    ParkingSpot parkingSpot;

    int priceCalculation() {
        return costComputation.getPrice(ticket);
    }

    void payment() {
        // pay.processPayment()
    }

    void freeSpace() {
        parkingSpotManager.removeVehicle(parkingSpot);
    }
}
