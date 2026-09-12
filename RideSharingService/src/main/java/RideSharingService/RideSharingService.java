package RideSharingService;

import Entities.Driver;
import Entities.Location;
import Entities.Trip;
import Entities.User;
import Enums.RideType;
import Observer.Rider;
import Strategy.DriverMatchingStrategy;
import Strategy.PricingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RideSharingService {

    private final List<Driver> drivers;

    private final DriverMatchingStrategy
            driverMatchingStrategy;

    private final PricingStrategy
            pricingStrategy;

    public RideSharingService(
            DriverMatchingStrategy driverMatchingStrategy,
            PricingStrategy pricingStrategy) {

        this.drivers = new ArrayList<>();

        this.driverMatchingStrategy =
                driverMatchingStrategy;

        this.pricingStrategy =
                pricingStrategy;
    }

    public void addDriver(Driver driver) {

        drivers.add(driver);

        System.out.println(
                "Driver added: " + driver.getName()
        );
    }

    public Trip requestRide(
            User user,
            Location pickup,
            Location destination,
            RideType rideType) {

        System.out.println();
        System.out.println(
                "===== RIDE REQUEST ====="
        );

        System.out.println(
                "Rider: " + user.getName()
        );

        System.out.println(
                "Pickup: " + pickup
        );

        System.out.println(
                "Destination: " + destination
        );

        Trip trip = new Trip(
                UUID.randomUUID().toString(),
                user,
                pickup,
                destination,
                pickup.distanceTo(destination)
        );

        // Observer registration
        trip.addObserver(new Rider(user));

        // Strategy Pattern
        Driver driver =
                driverMatchingStrategy.findDriver(
                        pickup,
                        rideType,
                        drivers
                );

        if (driver == null) {

            System.out.println(
                    "No suitable driver available."
            );

            return null;
        }

        // State Pattern
        trip.assignDriver(driver);

        // Strategy Pattern
        double fare =
                pricingStrategy.calculateFare(trip);

        trip.setFare(fare);

        System.out.println();
        System.out.println(
                "Driver Assigned: "
                        + driver.getName()
        );

        System.out.println(
                "Vehicle: "
                        + driver.getVehicle()
        );

        System.out.printf(
                "Estimated Fare: ₹%.2f%n",
                fare
        );

        return trip;
    }

    public void startRide(Trip trip) {

        System.out.println();
        System.out.println(
                "===== STARTING RIDE ====="
        );

        trip.startTrip();
    }

    public void completeRide(Trip trip) {

        System.out.println();
        System.out.println(
                "===== COMPLETING RIDE ====="
        );

        trip.completeTrip();

        System.out.printf(
                "Final Fare: ₹%.2f%n",
                trip.getFare()
        );
    }
}