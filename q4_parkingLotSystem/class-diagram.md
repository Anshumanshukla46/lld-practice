Design a Parking Lot System where:

- vehicles can enter
- vehicles can park
- vehicles can leave
- system should calculate parking fees

Support:
- Car
- Bike
- Truck

System should:
- show available slots
- prevent double parking
- allocate correct slot type

You can ignore:
- payment gateway
- multiple floors initially
- reservations
- admin/authentication

----
My:

Vehicle <Enum>:
- Car
- Bike
- Truck

VehicleInformation:
- vehicleNumber: int
- vehicle: Vehicle
- numberOfHours: int 
- paidFees: int <exclude>

ParkingSlot
- slotId: int
- slotType: Vehicle
- occupied: boolean
- parkedVehicle: VehicleInformation

ParkingLotService:
- List<ParkingSlot>

+ ParkingSlot vehicleEntered(vehicle: Vehicle, numberOfhours: int): ParkingSlot
  - showAvailableSlotByVehicle(vehicle): List<Integer> // lock of this List<Integer>
    - then check if list is empty = no slot available for that vehicle type
    - else pick 0th indexed as parking spot and add that to that specific list
    - add the numberOfHours to vehicle
    - get fees from getFees(vehicle, numberOfhours)
    - add in list of ParkingSlot
    - return ParkingSlot

- int getFees(vehicle: Vehicle, numberOfhours:int)
  - if bike: 
    - then numberOfhours * 10
  - if car: 
    - then numberOfhours * 20
  - if truck:
    - then numberOfhours * 50

+ boolean vehicleLeaved(vehicleNumber: int, vehicle: Vehicle)
  - by vehicle check in that list of ParkingSlot if that vehicle number is their in VehicleInformation
  - if so free that
  - else no available


+ List<Integer> showAvailableSlotByVehicle(vehicle : Vehicle)
  - assuming total 25 slot of bike, 50 of car, 10 of truck
  - if bike came:
    - iterate in List<ParkingSlot>, if slot for bike more than 25 then no else get whole list 
  - if car came: 
    - iterate in List<ParkingSlot>, if slot for car more than 50 then no else get whole list
  - if car came:
    - iterate in List<ParkingSlot>, if slot for truck more than 10 then no else get whole list
  - return that whole list

---
Correct:
========================
PARKING LOT SYSTEM
========================


ASSUMPTIONS
-----------
1. Fixed parking slots:
  - 25 Bike slots
  - 50 Car slots
  - 25 Truck slots

2. One vehicle occupies exactly one slot

3. Vehicle can only park in matching slot type

4. Fee is calculated based on:
  - vehicle type
  - number of hours


--------------------------------------------------
ENUMS
--------------------------------------------------


VehicleType <<Enumeration>>
----------------------------
- BIKE
- CAR
- TRUCK


SlotStatus <<Enumeration>>
---------------------------
- FREE
- OCCUPIED

--------------------------------------------------
ENTITIES
-------------------------------------------------

VehicleInformation
------------------
- vehicleNumber: String
- vehicleType: VehicleType
- entryTime: LocalDateTime
- numberOfHours: int
- paidFees: int


Explanation:
- Stores details of parked vehicle
- Represents active parking information


--------------------------------------------------

ParkingSlot
------------
- slotId: int
- slotType: VehicleType
- status: SlotStatus
- parkedVehicle: VehicleInformation


Explanation:
- Core entity of system
- Each slot has fixed slot type
- slotType NEVER changes after initialization
- parkedVehicle == null means slot is free

--------------------------------------------------

ParkingReceipt
---------------
- receiptId: long
- slotId: int
- vehicleNumber: String
- feesPaid: int
- entryTime: LocalDateTime


Explanation:
- Returned after successful parking
- Represents parking confirmation


--------------------------------------------------

ParkingLotService
-----------------
- parkingSlots: List<ParkingSlot>

+ initializeSlots(): void

+ vehicleEntered(
  vehicleNumber: String,
  vehicleType: VehicleType,
  numberOfHours: int
  ): ParkingReceipt

+ vehicleLeaved(
  vehicleNumber: String
  ): boolean

+ showAvailableSlotsByVehicle(
  vehicleType: VehicleType
  ): List<ParkingSlot>

- getAvailableSlot(
  vehicleType: VehicleType
  ): ParkingSlot

- getFees(
  vehicleType: VehicleType,
  numberOfHours: int
  ): int


--------------------------------------------------
RELATIONSHIPS
--------------------------------------------------


ParkingLotService --------> ParkingSlot
One-to-Many


ParkingSlot --------> VehicleInformation
One-to-One


ParkingReceipt --------> ParkingSlot
Many-to-One


--------------------------------------------------
SLOT INITIALIZATION
--------------------------------------------------


Bike Slots:
1 - 25

Car Slots:
26 - 75

Truck Slots:
76 - 100


Example:

Slot 1:
- slotType = BIKE

Slot 50:
- slotType = CAR

Slot 90:
- slotType = TRUCK


--------------------------------------------------
VEHICLE ENTRY FLOW
--------------------------------------------------


1. User enters:
  - vehicle number
  - vehicle type
  - parking duration


2. System finds:
   first FREE slot
   matching vehicle type


3. Lock per slot:

   synchronized(slot)

Explanation:
- prevents double parking
- only one thread can occupy slot


4. Recheck slot status INSIDE lock


5. Create VehicleInformation


6. Mark slot:
  - OCCUPIED
  - parkedVehicle assigned


7. Generate ParkingReceipt


--------------------------------------------------
VEHICLE EXIT FLOW
--------------------------------------------------


1. Find slot using vehicle number


2. Lock slot


3. Remove parkedVehicle


4. Mark slot FREE


5. Return success


--------------------------------------------------
IMPORTANT DESIGN INSIGHTS
--------------------------------------------------


1. Slot type is FIXED
--------------------------------
A bike slot always remains bike slot


2. Occupancy is DYNAMIC
--------------------------------
Only:
- status
- parkedVehicle

change during runtime


3. Locking should happen on SLOT
--------------------------------
Because slot occupancy is shared mutable state


4. Why ParkingSlot entity exists
--------------------------------
Parking slot has:
- state
- lifecycle
- ownership
- allocation behavior

So it deserves separate entity


5. Why List<Integer> was weak
--------------------------------
Primitive integers could not model:
- slot state
- occupancy
- parked vehicle
- slot type

