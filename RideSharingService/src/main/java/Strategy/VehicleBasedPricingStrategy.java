package Strategy;

import Entities.Trip;
import Enums.RideType;

public class VehicleBasedPricingStrategy
        implements PricingStrategy {

    @Override
    public double calculateFare(Trip trip) {

        double baseFare;
        double ratePerKm;

        RideType type =
                trip.getDriver()
                        .getVehicle()
                        .getRideType();

        switch (type) {

            case BIKE:
                baseFare = 20;
                ratePerKm = 8;
                break;

            case SEDAN:
                baseFare = 50;
                ratePerKm = 15;
                break;

            case SUV:
                baseFare = 80;
                ratePerKm = 20;
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported ride type"
                );
        }

        return baseFare + trip.getDistance() * ratePerKm;
    }
}