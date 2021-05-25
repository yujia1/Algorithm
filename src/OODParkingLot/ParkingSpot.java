package OODParkingLot;

class ParkingSpot {
    private final VehicleSize size;
    private Vehicle currentVehicle;
    ParkingSpot(VehicleSize size) {
        this.size = size;
    }
    boolean fit(Vehicle v) {
        return currentVehicle == null && size.getSize() >= v.getsize().getSize();
    }

    void park(Vehicle v) {
        currentVehicle = v;
    }
    void leave() {
        currentVehicle = null;
    }
    Vehicle getVehicle() {
        return currentVehicle;
    }
}
