package State;

import Entities.Driver;
import Entities.Trip;

public interface TripState {

    void assignDriver(Trip trip, Driver driver);

    void startTrip(Trip trip);

    void completeTrip(Trip trip);
}