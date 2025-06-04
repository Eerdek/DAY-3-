package kass_java.service;

import kass_java.Model.Order;
import kass_java.Model.OrderItem;
import kass_java.Model.Product;
import kass_java.repository.ProductRepository;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Service for reporting and analytics on orders
 */
public class ReportingService {
    private final OrderService orderService;
    private final ProductRepository productRepository;

    public ReportingService(OrderService orderService, ProductRepository productRepository) {
        this.orderService = orderService;
        this.productRepository = productRepository;
    }

    /**
     * Get orders by date range
     */
    public List<Order> getOrdersByDateRange(Date startDate, Date endDate) {
        List<Order> allOrders = orderService.getAllOrders();
        List<Order> filteredOrders = new ArrayList<>();

        for (Order order : allOrders) {
            Date orderDate = order.getDate();
            if (!orderDate.before(startDate) && !orderDate.after(endDate)) {
                filteredOrders.add(order);
            }
        }

        filteredOrders.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));

        return filteredOrders;
    }

    /**
     * Get daily sales report
     */
    public Map<String, Object> getDailySalesReport(Date date) {
        Map<String, Object> report = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(date);

        List<Order> allOrders = orderService.getAllOrders();
        List<Order> dailyOrders = new ArrayList<>();

        double totalSales = 0;
        int totalOrders = 0;

        for (Order order : allOrders) {
            String orderDateStr = dateFormat.format(order.getDate());
            if (orderDateStr.equals(dateStr) && order.getStatus().equals("Completed")) {
                dailyOrders.add(order);
                totalSales += order.getTotal();
                totalOrders++;
            }
        }

        report.put("date", dateStr);
        report.put("totalSales", totalSales);
        report.put("totalOrders", totalOrders);
        report.put("orders", dailyOrders);

        return report;
    }

    /**
     * Get popular products by quantity sold in a date range
     */
    public List<Map<String, Object>> getPopularProducts(Date startDate, Date endDate) {
        Map<Integer, Integer> productQuantities = new HashMap<>();

        List<Order> orders = getOrdersByDateRange(startDate, endDate);

        for (Order order : orders) {
            if (!order.getStatus().equals("Completed"))
                continue;

            for (OrderItem item : order.getItems()) {
                int productId = item.getProductID();
                int quantity = item.getQty();

                productQuantities.put(productId,
                        productQuantities.getOrDefault(productId, 0) + quantity);
            }
        }

        List<Map<String, Object>> popularProducts = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();

            Product product = productRepository.getProductById(productId);
            if (product != null) {
                Map<String, Object> productInfo = new HashMap<>();
                productInfo.put("productId", productId);
                productInfo.put("name", product.getName());
                productInfo.put("quantitySold", quantity);
                productInfo.put("revenue", product.getPrice() * quantity);

                popularProducts.add(productInfo);
            }
        }

        popularProducts.sort((p1, p2) -> {
            int qty1 = (int) p1.get("quantitySold");
            int qty2 = (int) p2.get("quantitySold");
            return Integer.compare(qty2, qty1);
        });

        return popularProducts;
    }

    /**
     * Get revenue by waiter
     */
    public Map<String, Double> getRevenueByWaiter(Date startDate, Date endDate) {
        Map<String, Double> waiterRevenue = new HashMap<>();

        List<Order> orders = getOrdersByDateRange(startDate, endDate);

        for (Order order : orders) {
            if (!order.getStatus().equals("Completed"))
                continue;

            String waiter = order.getWaiterName();
            double total = order.getTotal();

            waiterRevenue.put(waiter, waiterRevenue.getOrDefault(waiter, 0.0) + total);
        }

        return waiterRevenue;
    }
}
