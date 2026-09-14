package Strategy;

import Entities.Payment;

public interface PaymentStrategy {
    Payment pay(double amount);
}