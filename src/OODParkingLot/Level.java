package src.OODParkingLot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Level {
    private final List<ParkingSpot> spots;
    // why it's no modifier ???
    // 只需要进入 package
    Level(int numOfSpots) {
        List<ParkingSpot> list = new ArrayList<>(numOfSpots);
        int i = 0;
        for (; i < numOfSpots / 2; i++) {
            list.add(new ParkingSpot(VehicleSize.Compact));
        }
        for (; i< numOfSpots/2; i++) {
            list.add(new ParkingSpot(VehicleSize.Large));
        }
        // make this list read-only
        spots = Collections.unmodifiableList(list);
    }
    boolean hasSpot(Vehicle v) {
        for (ParkingSpot s: spots) {
            if (s.fit(v)) {
                return true;
            }
        }
        return false;
    }
    boolean park(Vehicle v) {
        for (ParkingSpot s: spots) {
            if (s.fit(v)) {
                s.park(v);
                return true;
            }
        }
        return false;
    }
    boolean leave(Vehicle v) {
        for (ParkingSpot s: spots) {
            if (s.getVehicle() == v) {
                s.leave();
                return true;
            }
        }
        return false;
    }

}
