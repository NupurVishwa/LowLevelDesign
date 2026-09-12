package Strategy;

import Entities.Trip;

public interface PricingStrategy {

    double calculateFare(Trip trip);
}