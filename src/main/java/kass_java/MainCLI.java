package kass_java;

import kass_java.service.ProductService;
import kass_java.Model.Product;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainCLI {
    private static final Logger logger = LogManager.getLogger(MainCLI.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();

        logger.info("Welcome to Kass CLI Product Manager");

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
                logger.warn("Invalid input. Not a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.print("Category ID: ");
                        int categoryId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Category Name: ");
                        String category = scanner.nextLine();

                        productService.addProduct(name, price, categoryId, null, category);
                        logger.info("Product added: {} (₮{})", name, price);
                    } catch (Exception e) {
                        logger.error("Error while adding product", e);
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter product ID to remove: ");
                        int removeId = Integer.parseInt(scanner.nextLine());
                        boolean removed = productService.removeProductById(removeId);
                        if (removed) {
                            logger.info("Product removed (ID: {})", removeId);
                        } else {
                            logger.warn("Product ID not found: {}", removeId);
                        }
                    } catch (Exception e) {
                        logger.error("Error removing product", e);
                    }
                    break;

                case 3:
                    logger.info("Listing all products");
                    productService.listProducts();
                    break;

                case 4:
                    try {
                        System.out.print("Enter product ID to update: ");
                        int updateId = Integer.parseInt(scanner.nextLine());

                        Product existing = productService.findProductById(updateId);
                        if (existing == null) {
                            logger.warn("Product not found with ID: {}", updateId);
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

                        boolean updated = productService.updateProduct(updateId,
                                newName.isEmpty() ? existing.getName() : newName,
                                newPrice,
                                newCategoryId,
                                null,
                                newCategory.isEmpty() ? existing.getCategory() : newCategory);

                        if (updated) {
                            logger.info("Product updated: {}", updateId);
                        } else {
                            logger.warn("Failed to update product: {}", updateId);
                        }
                    } catch (Exception e) {
                        logger.error("Error updating product", e);
                    }
                    break;

                case 0:
                    logger.info("Exiting CLI. Peace out ");
                    return;

                default:
                    logger.warn("Unknown menu choice: {}", choice);
            }
        }
    }
}
