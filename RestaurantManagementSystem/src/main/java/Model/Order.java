package Model;

import Enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final int orderId;
    private final int tableId;
    private OrderStatus status;
    private final List<OrderItem> orderItems;

    public Order(int orderId, int tableId) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.status = OrderStatus.PLACED;
        this.orderItems = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    // Compatibility method
    public int getId() {
        return orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addItem(OrderItem orderItem) {
        if (orderItem == null) {
            throw new IllegalArgumentException(
                    "Order item cannot be null."
            );
        }

        orderItems.add(orderItem);
    }

    public double getTotalPrice() {
        double total = 0.0;

        for (OrderItem orderItem : orderItems) {
            total += orderItem.getMenuItem().getPrice();
        }

        return total;
    }

    // Compatibility method
    public double calculateTotal() {
        return getTotalPrice();
    }

    public void markPreparing() {
        status = OrderStatus.PREPARING;
    }

    public void markReady() {
        status = OrderStatus.READY;
    }

    public void markServed() {
        status = OrderStatus.SERVED;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", tableId=" + tableId +
                ", status=" + status +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}

