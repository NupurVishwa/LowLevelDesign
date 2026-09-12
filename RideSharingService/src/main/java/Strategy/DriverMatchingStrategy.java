package Strategy;

import Entities.Driver;
import Entities.Location;
import Enums.RideType;

import java.util.List;

public interface DriverMatchingStrategy {

    Driver findDriver(
            Location pickupLocation,
            RideType rideType,
            List<Driver> drivers
    );
}