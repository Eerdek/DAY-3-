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
        System.out.println("Kass CLI Product Manager started");

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
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        double price;
                        while (true) {
                            System.out.print("Price: ");
                            try {
                                price = Double.parseDouble(scanner.nextLine());
                                if (price < 0) throw new NumberFormatException();
                                break;
                            } catch (NumberFormatException e) {
                                logger.warn("Invalid price.");
                                System.out.println("Enter a valid positive number.");
                            }
                        }

                        int categoryId;
                        while (true) {
                            System.out.print("Category ID: ");
                            try {
                                categoryId = Integer.parseInt(scanner.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                logger.warn("Invalid category ID.");
                                System.out.println("Enter a valid integer.");
                            }
                        }

                        System.out.print("Category Name: ");
                        String category = scanner.nextLine();

                        productService.addProduct(name, price, categoryId, null, category);
                        logger.info("Product added: {} (₮{})", name, price);
                        System.out.println("Product added successfully.");
                    } catch (Exception e) {
                        logger.error("Error while adding product", e);
                        System.out.println("Error adding product.");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter product ID to remove: ");
                        int removeId = Integer.parseInt(scanner.nextLine());
                        boolean removed = productService.removeProductById(removeId);
                        if (removed) {
                            logger.info("Product removed (ID: {})", removeId);
                            System.out.println("Product successfully removed.");
                        } else {
                            logger.warn("Product ID not found: {}", removeId);
                            System.out.println("Product not found.");
                        }
                    } catch (Exception e) {
                        logger.error("Error removing product", e);
                        System.out.println("Invalid input.");
                    }
                    break;

                case 3:
                    logger.info("Listing all products");
                    System.out.println("Product List:");
                    productService.listProducts();
                    break;

                case 4:
                    try {
                        System.out.print("Enter product ID to update: ");
                        int updateId = Integer.parseInt(scanner.nextLine());

                        Product existing = productService.findProductById(updateId);
                        if (existing == null) {
                            logger.warn("Product not found with ID: {}", updateId);
                            System.out.println("Product not found.");
                            break;
                        }

                        System.out.print("New Name [" + existing.getName() + "]: ");
                        String newName = scanner.nextLine();

                        double newPrice;
                        while (true) {
                            System.out.print("New Price [" + existing.getPrice() + "]: ");
                            String input = scanner.nextLine();
                            if (input.isEmpty()) {
                                newPrice = existing.getPrice();
                                break;
                            }
                            try {
                                newPrice = Double.parseDouble(input);
                                break;
                            } catch (NumberFormatException e) {
                                logger.warn("Invalid price input.");
                                System.out.println("Enter a valid number.");
                            }
                        }

                        int newCategoryId;
                        while (true) {
                            System.out.print("New Category ID [" + existing.getCategoryId() + "]: ");
                            String input = scanner.nextLine();
                            if (input.isEmpty()) {
                                newCategoryId = existing.getCategoryId();
                                break;
                            }
                            try {
                                newCategoryId = Integer.parseInt(input);
                                break;
                            } catch (NumberFormatException e) {
                                logger.warn("Invalid category ID input.");
                                System.out.println("Enter a valid integer.");
                            }
                        }

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
                            System.out.println("Product updated.");
                        } else {
                            logger.warn("Failed to update product: {}", updateId);
                            System.out.println("Update failed.");
                        }
                    } catch (Exception e) {
                        logger.error("Error updating product", e);
                        System.out.println("Error updating product.");
                    }
                    break;

                case 0:
                    logger.info("Exiting CLI. Peace out.");
                    System.out.println("Goodbye. Exiting Kass CLI.");
                    return;

                default:
                    logger.warn("Unknown choice: {}", choice);
                    System.out.println("Invalid option.");
            }

            scanner.close();
        }
    }
}
