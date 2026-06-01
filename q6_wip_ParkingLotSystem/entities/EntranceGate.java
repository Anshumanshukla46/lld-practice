package q6_wip_ParkingLotSystem.entities;

public class EntranceGate {
    // TODO
    private ParkingSpotFactory parkingSpotFactory;
    private ParkingSpotManager parkingSpotManager;
    private Ticket ticket;

    public void findSpace(VehicleType vehicleType){
        parkingSpotManager.findParkingSpace();
    }

    public void bookSpot(Vehicle vehicle){
        parkingSpotManager.parkVehicle(vehicle);
    }

    public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot){
        return new Ticket();
    }
}
