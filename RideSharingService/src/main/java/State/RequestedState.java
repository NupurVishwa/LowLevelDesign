package State;

import Entities.Driver;
import Entities.Trip;
import Enums.DriverStatus;
import Enums.TripStatus;

public class RequestedState implements TripState {

    @Override
    public void assignDriver(Trip trip, Driver driver) {

        trip.setDriver(driver);

        driver.setStatus(DriverStatus.BUSY);

        trip.setStatus(TripStatus.ASSIGNED);

        trip.setState(new AssignedState());

        trip.notifyObservers();
    }

    @Override
    public void startTrip(Trip trip) {

        throw new IllegalStateException(
                "Cannot start trip before driver is assigned"
        );
    }

    @Override
    public void completeTrip(Trip trip) {

        throw new IllegalStateException(
                "Cannot complete a requested trip"
        );
    }
}