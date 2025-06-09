package kass_java;

import java.util.*;

import kass_java.Model.*;
import kass_java.repository.*;
import kass_java.service.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            logger.info("===== Kass Backend Test START =====");

            Category drinks = new Category("Drinks");
            CategoryRepository catRepo = new CategoryRepository();
            catRepo.save(drinks);
            logger.info("Category added: {}", drinks.getName());

            Table t1 = new Table("Table A1");
            TableRepository tRepo = new TableRepository();
            tRepo.save(t1);
            logger.info("Table added: {}", t1.getName());

            byte[] image = new byte[0];
            Product p1 = new Product("Coca Cola", 2500.0, drinks.getId(), image, "Drink");
            ProductRepository pRepo = new ProductRepository();
            pRepo.save(p1);
            logger.info("Product added: {}", p1.getName());

            OrderItem item1 = new OrderItem(p1.getId(), 2, p1.getPrice());
            List<OrderItem> items = Arrays.asList(item1);

            Order order = new Order(
                new Date(), "14:30", t1.getName(), "Narantuya",
                "Dine In", item1.getAmount(), 0, 0,
                0, "", "", items
            );

            OrderService orderService = new OrderService();
            int newOrderId = orderService.saveOrUpdateOrder(order, false);
            logger.info("Order placed with ID: {}", newOrderId);

            CheckoutService checkout = new CheckoutService();
            boolean paid = checkout.completePayment(newOrderId, item1.getAmount(), 5000);
            logger.info(paid ? "Payment success!" : "Payment failed!");

            logger.info("===== Kass Backend Test DONE =====");

        } catch (Exception e) {
            logger.error("Exception occurred in Main: ", e);
        }
    }
}
