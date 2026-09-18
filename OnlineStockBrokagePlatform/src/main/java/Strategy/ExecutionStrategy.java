package Strategy;

import Entities.Order;

public interface ExecutionStrategy {
    boolean canExecute(Order order, double marketPrice);
}