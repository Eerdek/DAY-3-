package kass_java;
import java.util.*;

import kass_java.service.ProductService;

public class MainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService service = new ProductService();

        while (true) {
            System.out.println("\n=====Product management=====");
            System.out.println("1. Add product");
            System.out.println("2. Remove product");
            System.out.println("3. View all products");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // newline залгих

            switch (choice) {
                case 1 -> {
                    System.out.print("Product Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Price (₮): ");
                    double price = scanner.nextDouble();

                    System.out.print("Category ID: ");
                    int catId = scanner.nextInt();
                    scanner.nextLine(); // newline залгих

                    System.out.print("Category name: ");
                    String category = scanner.nextLine();

                    service.addProduct(name, price, catId, null, category);
                }
                case 2 -> {
                    System.out.print("Remove product ID: ");
                    int id = scanner.nextInt();
                    boolean success = service.removeProductById(id);
                    System.out.println(success ? "Successfully removed!" : "Product not found!");
                }
                case 3 -> service.listProducts();
                case 0 -> {
                    System.out.println("Exiting the program.");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
