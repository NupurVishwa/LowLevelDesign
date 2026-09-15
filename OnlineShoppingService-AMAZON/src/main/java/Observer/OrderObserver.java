package Observer;

import Model.Order;

public interface OrderObserver {
    void update(Order order);
}