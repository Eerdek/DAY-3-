package kass_java.service;

import kass_java.Model.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    ProductService productService;
    OrderService orderService;
    CheckoutService checkoutService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
        orderService = new OrderService();
        checkoutService = new CheckoutService();
    }

    @Test
    void testCompletePaymentSuccess() {
        productService.addProduct("Кофе", 3000.0, 1, null, "Ундаа");
        Product p = productService.getAllProducts().get(0);

        OrderItem item = new OrderItem(p.getId(), 1, p.getPrice());
        List<OrderItem> items = List.of(item);

        Order order = new Order(new Date(), "11:30", "Ширээ 3", "Захиалагч",
                "Take Away", item.getAmount(), 0, 0, 0, "", "", items);

        int orderId = orderService.saveOrUpdateOrder(order, false);

        boolean result = checkoutService.completePayment(orderId, item.getAmount(), 10000);
        assertTrue(result);
    }

    @Test
    void testCompletePaymentFail_NotEnough() {
        productService.addProduct("Жүүс", 4000.0, 1, null, "Ундаа");
        Product p = productService.getAllProducts().get(0);

        OrderItem item = new OrderItem(p.getId(), 1, p.getPrice());
        List<OrderItem> items = List.of(item);

        Order order = new Order(new Date(), "12:00", "Ширээ 4", "Хүн", "Dine In",
                item.getAmount(), 0, 0, 0, "", "", items);

        int orderId = orderService.saveOrUpdateOrder(order, false);

        boolean result = checkoutService.completePayment(orderId, item.getAmount(), 1000);
        assertFalse(result);
    }
}
