package State;

import Entities.Trip;
import Enums.DriverStatus;
import Enums.TripStatus;

public class InprogressState implements TripState {

    @Override
    public void assignDriver(
            Trip trip,
            Entities.Driver driver) {

        throw new IllegalStateException(
                "Cannot change driver during trip"
        );
    }

    @Override
    public void startTrip(Trip trip) {

        throw new IllegalStateException(
                "Trip is already in progress"
        );
    }

    @Override
    public void completeTrip(Trip trip) {

        trip.setStatus(TripStatus.COMPLETED);

        if (trip.getDriver() != null) {
            trip.getDriver()
                    .setStatus(DriverStatus.AVAILABLE);
        }

        trip.setState(new CompletedState());

        trip.notifyObservers();
    }
}