package kass_java.service;

import kass_java.Model.Order;
import kass_java.Model.OrderItem;
import kass_java.Model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class OrderServiceTest {
    OrderService orderService;
    ProductService productService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
        productService = new ProductService();
    }

    @Test
    void testSaveOrder() {
        productService.addProduct("Цай", 1500.0, 1, null, "Ундаа");
        Product p = productService.getAllProducts().get(0);

        OrderItem item = new OrderItem(p.getId(), 2, p.getPrice());
        List<OrderItem> items = List.of(item);

        Order order = new Order(new Date(), "10:00", "Ширээ 1", "Нараа",
                "Dine In", item.getAmount(), 0, 0, 0, "", "", items);

        int orderId = orderService.saveOrUpdateOrder(order, false);
        assertTrue(orderId > 0);
    }
}
