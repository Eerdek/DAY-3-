package kass_java.service;

import kass_java.Model.Order;
import kass_java.Model.OrderItem;
import kass_java.Model.Product;
import kass_java.repository.ProductRepository;

import java.util.*;

public class OrderService {
    private static int mainIdCounter = 1;
    private static int detailIdCounter = 1;

    private final Map<Integer, Order> orders = new HashMap<>();
    private final ProductRepository productRepository;

    public OrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Saves or updates an order after checking stock availability
     * 
     * @return The ID of the saved or updated order
     */
    public int saveOrUpdateOrder(Order order, boolean isHold) {
        Map<String, Object> result = new HashMap<>();

        if (!isHold) {
            for (OrderItem item : order.getItems()) {
                Product product = productRepository.getProductById(item.getProductID());
                if (product == null) {
                    result.put("success", false);
                    result.put("message", "Product with ID " + item.getProductID() + " not found");
                    return 0;
                }

                if (product.getStockQuantity() < item.getQty()) {
                    result.put("success", false);
                    result.put("message", "Insufficient stock for " + product.getName() +
                            ". Available: " + product.getStockQuantity() + ", Requested: " + item.getQty());
                    return 0;
                }
            }
        }

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

        if (!isHold) {
            for (OrderItem item : order.getItems()) {
                Product product = productRepository.getProductById(item.getProductID());
                if (product != null) {
                    product.setStockQuantity(product.getStockQuantity() - item.getQty());
                    productRepository.save(product);
                }
            }
        }

        result.put("success", true);
        result.put("orderId", order.getId());
        return order.getId();
    }

    public Order getOrderById(int id) {
        return orders.get(id);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
}
