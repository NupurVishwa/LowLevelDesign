package Observer;

import Order.Order;

public interface OrderObserver {
    void onUpdate(Order order);
}
