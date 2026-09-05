package Observer;

import Model.OrderItem;

public interface OrderObserver {
    void update(OrderItem item);
}