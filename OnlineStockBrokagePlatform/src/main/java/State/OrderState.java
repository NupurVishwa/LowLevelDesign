package State;

import Entities.Order;

public interface OrderState {
    void handle(Order order);
    void cancel(Order order);
}