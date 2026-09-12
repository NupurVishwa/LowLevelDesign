package State;

import Entities.Driver;
import Entities.Trip;

public class CompletedState implements TripState {

    @Override
    public void assignDriver(Trip trip, Driver driver) {

        throw new IllegalStateException(
                "Trip is already completed"
        );
    }

    @Override
    public void startTrip(Trip trip) {

        throw new IllegalStateException(
                "Trip is already completed"
        );
    }

    @Override
    public void completeTrip(Trip trip) {

        throw new IllegalStateException(
                "Trip is already completed"
        );
    }
}