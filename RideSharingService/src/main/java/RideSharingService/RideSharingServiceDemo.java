package RideSharingService;

import Entities.Driver;
import Entities.Location;
import Entities.Trip;
import Entities.User;
import Entities.Vehicle;
import Enums.RideType;
import Strategy.NearestDriverMatchingStrategy;
import Strategy.VehicleBasedPricingStrategy;

public class RideSharingServiceDemo {

    public static void main(String[] args) {

        System.out.println(
                "========================================"
        );

        System.out.println(
                "       RIDE SHARING SERVICE"
        );

        System.out.println(
                "========================================"
        );

        // ------------------------------------
        // USERS
        // ------------------------------------

        User user1 =
                new User(
                        "U1",
                        "Nupur",
                        "9876543210"
                );

        User user2 =
                new User(
                        "U2",
                        "Rahul",
                        "9876543211"
                );

        // ------------------------------------
        // VEHICLES
        // ------------------------------------

        Vehicle car1 =
                new Vehicle(
                        "V1",
                        "GJ01AB1234",
                        RideType.SEDAN
                );

        Vehicle car2 =
                new Vehicle(
                        "V2",
                        "GJ01CD5678",
                        RideType.SUV
                );

        Vehicle bike =
                new Vehicle(
                        "V3",
                        "GJ01EF9999",
                        RideType.BIKE
                );

        // ------------------------------------
        // DRIVERS
        // ------------------------------------

        Driver driver1 =
                new Driver(
                        "D1",
                        "Amit",
                        new Location(
                                23.0225,
                                72.5714
                        ),
                        car1
                );

        Driver driver2 =
                new Driver(
                        "D2",
                        "Raj",
                        new Location(
                                23.0300,
                                72.5800
                        ),
                        car2
                );

        Driver driver3 =
                new Driver(
                        "D3",
                        "Vikram",
                        new Location(
                                23.0500,
                                72.5900
                        ),
                        bike
                );

        // ------------------------------------
        // SERVICE
        // ------------------------------------

        RideSharingService service =
                new RideSharingService(
                        new NearestDriverMatchingStrategy(),
                        new VehicleBasedPricingStrategy()
                );

        // ------------------------------------
        // ADD DRIVERS
        // ------------------------------------

        service.addDriver(driver1);
        service.addDriver(driver2);
        service.addDriver(driver3);

        // ------------------------------------
        // REQUEST RIDE
        // ------------------------------------

        Location pickup =
                new Location(
                        23.0250,
                        72.5700
                );

        Location destination =
                new Location(
                        23.0500,
                        72.6000
                );

        Trip trip =
                service.requestRide(
                        user1,
                        pickup,
                        destination,
                        RideType.SEDAN
                );

        // ------------------------------------
        // START RIDE
        // ------------------------------------

        if (trip != null) {

            System.out.println();
            System.out.println(
                    "Current Trip:"
            );

            System.out.println(trip);

            service.startRide(trip);

            // --------------------------------
            // COMPLETE RIDE
            // --------------------------------

            service.completeRide(trip);

            System.out.println();
            System.out.println(
                    "Final Trip:"
            );

            System.out.println(trip);
        }
    }
}