package OODParkingLot;

public class Car extends Vehicle {
    @Override
    public VehicleSize getsize() {
        return VehicleSize.Compact;
    }
}
