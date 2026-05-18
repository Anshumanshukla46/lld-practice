package q4_parkingLotSystem.src.service;

import q4_parkingLotSystem.src.entity.ParkingSlot;
import q4_parkingLotSystem.src.entity.Vehicle;
import q4_parkingLotSystem.src.entity.VehicleInformation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {
    private static List<ParkingSlot> parkingSlotList;

    private static void initialiseSlot() {
        parkingSlotList = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            ParkingSlot parkingSlot = new ParkingSlot(i, Vehicle.BIKE, false, null);
            parkingSlotList.add(parkingSlot);
        }

        for (int i = 25; i <= 75; i++) {
            ParkingSlot parkingSlot = new ParkingSlot(i, Vehicle.CAR, false, null);
            parkingSlotList.add(parkingSlot);
        }

        for (int i = 76; i <= 100; i++) {
            ParkingSlot parkingSlot = new ParkingSlot(i, Vehicle.TRUCK, false, null);
            parkingSlotList.add(parkingSlot);
        }

    }

    public ParkingSlot vehicleEntered(Vehicle vehicleType, int numberOfHours) {
        ParkingSlot slot = new ParkingSlot();
        for (ParkingSlot parkingSlot : parkingSlotList) {
            if (!parkingSlot.isOccupied() && parkingSlot.getSlotType().equals(vehicleType)) {
                slot = parkingSlot;
                break;
            }
        }

        synchronized (slot) {
            if (!slot.isOccupied() && slot.getSlotType().equals(vehicleType)) {
                VehicleInformation vehicleInformation = new VehicleInformation(vehicleType, numberOfHours, getFees(vehicleType, numberOfHours));
                slot.setOccupied(true);
                slot.setParkedVehicle(vehicleInformation);
            }
        }
        return slot;
    }

    private int getFees(Vehicle vehicle, int numberOfHours) {
        if (Vehicle.BIKE.equals(vehicle)) {
            return numberOfHours * 10;
        } else if (Vehicle.CAR.equals(vehicle)) {
            return numberOfHours * 20;
        } else {
            return numberOfHours * 50;
        }
    }

    public ParkingSlot vehicleLeaved(ParkingSlot parkingSlot) {
        ParkingSlot slot = new ParkingSlot();
        if (parkingSlot == null)
            return slot;

        for (ParkingSlot temp : parkingSlotList) {
            if (temp.getParkedVehicle() != null && temp.getParkedVehicle().equals(parkingSlot.getParkedVehicle())) {
                synchronized (temp) {
                    temp.setParkedVehicle(null);
                    temp.setOccupied(false);
                    slot = temp;
                }
            }
        }

        return slot;
    }

    public List<ParkingSlot> showAvailableSlotByVehicle(Vehicle slotType) {
        List<ParkingSlot> res = new ArrayList<>();
        for (ParkingSlot parkingSlot : parkingSlotList) {
            if (parkingSlot.getSlotType().equals(slotType) && !parkingSlot.isOccupied()) {
                res.add(parkingSlot);
            }
        }
        return res;
    }
}
