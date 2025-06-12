package kass_java.service;

import java.util.*;

import kass_java.Model.Order;
import kass_java.Model.OrderItem;

/**
 * Service class for managing Order operations in the KASS Java POS System.
 * This class provides business logic for order management including saving, updating,
 * and retrieving orders. Orders are stored in-memory with automatic ID generation
 * for both main orders and order detail items.
 * 
 * @author KASS Java POS System
 * @version 1.0
 * @since 2025-06-12
 */
public class OrderService {
    /**
     * Static counter for generating unique main IDs for orders.
     * Starts at 1 and increments for each new order created.
     * This ensures each order has a unique identifier across the application.
     */
    private static int mainIdCounter = 1;
    
    /**
     * Static counter for generating unique detail IDs for order items.
     * Starts at 1 and increments for each new order item created.
     * This ensures each order item has a unique identifier within orders.
     */
    private static int detailIdCounter = 1;

    /**
     * In-memory storage for orders using HashMap.
     * Maps order ID (Integer) to Order objects for efficient retrieval.
     */
    private final Map<Integer, Order> orders = new HashMap<>();

    /**
     * Saves a new order or updates an existing order with status management.
     * This method handles both new order creation and existing order updates.
     * For new orders, it assigns a unique ID and sets the initial status based on the hold flag.
     * For existing orders, it updates the status based on the hold flag.
     * All order items without detail IDs are assigned unique detail IDs.
     * 
     * @param order the Order object to save or update. Must not be null.
     * @param isHold flag indicating whether the order should be placed on hold.
     *               If true, status is set to "Hold"; if false, status is set to "Pending".
     * @return the ID of the saved or updated order
     * @throws NullPointerException if order is null or if order.getItems() returns null
     */
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

    /**
     * Retrieves an order by its unique ID.
     * This method performs a direct lookup in the orders map using the provided ID.
     * 
     * @param id the unique identifier of the order to retrieve
     * @return the Order object with the specified ID, or null if no order 
     *         with the given ID exists in the service
     */
    public Order getOrderById(int id) {
        return orders.get(id);
    }

    /**
     * Retrieves all orders managed by this service.
     * This method returns a defensive copy of all orders to prevent external modification
     * of the internal order storage.
     * 
     * @return a new ArrayList containing all orders in the service.
     *         Returns an empty list if no orders are stored.
     *         The returned list is a defensive copy, so modifications won't affect the service's data.
     */
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
}
