package kass_java.service;

import kass_java.Model.Order;
import kass_java.Model.OrderItem;
import kass_java.Model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


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

    //  Шинэчилсэн хэсэг
    assertThrows(IllegalArgumentException.class, () -> {
        checkoutService.completePayment(orderId, item.getAmount(), 1000);
    });
}

}
