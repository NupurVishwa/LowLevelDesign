package Strategy;

import Entities.Driver;
import Entities.Location;
import Enums.DriverStatus;
import Enums.RideType;

import java.util.List;

public class NearestDriverMatchingStrategy
        implements DriverMatchingStrategy {

    @Override
    public Driver findDriver(
            Location pickupLocation,
            RideType rideType,
            List<Driver> drivers) {

        Driver nearestDriver = null;
        double minimumDistance = Double.MAX_VALUE;

        for (Driver driver : drivers) {

            if (driver.getStatus() != DriverStatus.AVAILABLE) {
                continue;
            }

            if (driver.getVehicle().getRideType() != rideType) {
                continue;
            }

            double distance =
                    driver.getCurrentLocation()
                            .distanceTo(pickupLocation);

            if (distance < minimumDistance) {
                minimumDistance = distance;
                nearestDriver = driver;
            }
        }

        return nearestDriver;
    }
}