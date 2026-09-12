package State;

import Entities.Driver;
import Entities.Trip;
import Enums.TripStatus;

public class AssignedState implements TripState {

    @Override
    public void assignDriver(Trip trip, Driver driver) {

        throw new IllegalStateException(
                "Driver is already assigned"
        );
    }

    @Override
    public void startTrip(Trip trip) {

        trip.setStatus(TripStatus.IN_PROGRESS);

        trip.setState(new InprogressState());

        trip.notifyObservers();
    }

    @Override
    public void completeTrip(Trip trip) {

        throw new IllegalStateException(
                "Trip has not started yet"
        );
    }
}