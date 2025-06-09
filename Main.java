
import java.util.*;

import kass_java.Model.*;
import kass_java.repository.*;
import kass_java.service.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("===== Kass Backend Test =====");

          
            Category drinks = new Category("Drinks");
            CategoryRepository catRepo = new CategoryRepository();
            catRepo.save(drinks);
            System.out.println("Category added: " + drinks.getName());

           
            Table t1 = new Table("Table A1");
            TableRepository tRepo = new TableRepository();
            tRepo.save(t1);
            System.out.println("Table added: " + t1.getName());

           
            byte[] image = new byte[0];

            Product p1 = new Product("Coca Cola", 2500.0, 1, image,"Drink");
            ProductRepository pRepo = new ProductRepository();
            pRepo.save(p1);
            System.out.println("Product added: " + p1.getName());

       
            OrderItem item1 = new OrderItem(p1.getId(), 2, p1.getPrice());
            List<OrderItem> items = Arrays.asList(item1);

            Order order = new Order(
                new Date(), "14:30", t1.getName(), "Narantuya",
                "Dine In", item1.getAmount(), 0, 0,
                0, "", "", items
            );

            OrderService orderService = new OrderService();
            int newOrderId = orderService.saveOrUpdateOrder(order, false);
            System.out.println("Order placed with ID: " + newOrderId);


            CheckoutService checkout = new CheckoutService();
            boolean paid = checkout.completePayment(newOrderId, item1.getAmount(), 5000);
            System.out.println(paid ? "Payment success!" : "Payment failed!");

            System.out.println("===== DONE =====");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
