package kass_java;

import kass_java.service.ProductService;
import kass_java.Model.Product;

import java.util.Scanner;

public class MainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();

        System.out.println("Welcome to Kass CLI Product Manager\n");

        while (true) {
            System.out.println("\n===== Product Management =====");
            System.out.println("1. Add product");
            System.out.println("2. Remove product");
            System.out.println("3. View all products");
            System.out.println("4. Update product");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Category ID: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Category Name: ");
                    String category = scanner.nextLine();

                    productService.addProduct(name, price, categoryId, null, category);
                    System.out.println("Product added.");
                    break;

                case 2:
                    System.out.print("Enter product ID to remove: ");
                    int removeId = Integer.parseInt(scanner.nextLine());
                    boolean removed = productService.removeProductById(removeId);
                    if (removed) {
                        System.out.println("Product removed.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    productService.listProducts();
                    break;

                case 4:
                    System.out.print("Enter product ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());

                    Product existing = productService.findProductById(updateId);
                    if (existing == null) {
                        System.out.println("Product not found.");
                        break;
                    }

                    System.out.print("New Name [" + existing.getName() + "]: ");
                    String newName = scanner.nextLine();
                    System.out.print("New Price [" + existing.getPrice() + "]: ");
                    double newPrice = Double.parseDouble(scanner.nextLine());
                    System.out.print("New Category ID [" + existing.getCategoryId() + "]: ");
                    int newCategoryId = Integer.parseInt(scanner.nextLine());
                    System.out.print("New Category [" + existing.getCategory() + "]: ");
                    String newCategory = scanner.nextLine();

                    productService.updateProduct(updateId,
                            newName.isEmpty() ? existing.getName() : newName,
                            newPrice,
                            newCategoryId,
                            null,
                            newCategory.isEmpty() ? existing.getCategory() : newCategory);
                    System.out.println("Product updated.");
                    break;

                case 0:
                    System.out.println("Exiting CLI. Peace out.");
                    return;

                default:
                    System.out.println("Unknown choice. Try again.");
            }
        }
    }
}
