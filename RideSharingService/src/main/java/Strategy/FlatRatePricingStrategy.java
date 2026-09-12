package Strategy;

import Entities.Trip;

public class FlatRatePricingStrategy
        implements PricingStrategy {

    private final double ratePerKm;

    public FlatRatePricingStrategy(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    @Override
    public double calculateFare(Trip trip) {

        return trip.getDistance() * ratePerKm;
    }
}