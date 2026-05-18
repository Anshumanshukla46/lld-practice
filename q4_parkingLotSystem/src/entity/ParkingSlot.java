package q4_parkingLotSystem.src.entity;

public class ParkingSlot {
    private int slotId;
    private Vehicle slotType;
    private boolean occupied;
    private VehicleInformation parkedVehicle;

    public ParkingSlot() {
    }

    public ParkingSlot(int slotId, Vehicle slotType, boolean occupied, VehicleInformation parkedVehicle) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.occupied = occupied;
        this.parkedVehicle = parkedVehicle;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Vehicle getSlotType() {
        return slotType;
    }

    public void setSlotType(Vehicle slotType) {
        this.slotType = slotType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public VehicleInformation getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(VehicleInformation parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }
}
