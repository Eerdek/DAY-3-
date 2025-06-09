package kass_java.service;

import java.util.*;

import kass_java.Model.Order;
import kass_java.Model.OrderItem;

public class OrderService {
    private static int mainIdCounter = 1;
    private static int detailIdCounter = 1;

    private final Map<Integer, Order> orders = new HashMap<>();

    public int saveOrUpdateOrder(Order order, boolean isHold) {
        boolean isNewOrder = order.getId() == 0;

        if (isNewOrder) {
            order.setId(mainIdCounter++);
            if (!isHold) {
                order.setStatus("Pending");
            } else {
                order.setStatus("Hold");
            }
        } else {
            Order existing = orders.get(order.getId());
            if (existing != null) {
                order.setStatus(isHold ? "Hold" : "Pending");
            }
        }

        for (OrderItem item : order.getItems()) {
            if (item.getDetailID() == 0) {
                item.setDetailID(detailIdCounter++);
            }
        }

        orders.put(order.getId(), order);
        return order.getId();
    }

    public Order getOrderById(int id) {
        return orders.get(id);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
}
