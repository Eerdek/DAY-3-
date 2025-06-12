package kass_java.service;

import java.util.*;
import kass_java.Model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service class for managing Product operations in the KASS Java POS System.
 * This class provides business logic for product management including CRUD operations,
 * validation, and product listing functionality. Products are stored in-memory
 * with automatic ID generation and comprehensive logging.
 * 
 * @author KASS Java POS System
 * @version 1.0
 * @since 2025-06-12
 */
public class ProductService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    /**
     * In-memory storage for products using ArrayList.
     * This list maintains all products managed by the service.
     */
    private List<Product> products = new ArrayList<>();
    
    /**
     * Counter for generating unique IDs for new products.
     * Starts at 1 and increments for each new product added.
     */
    private int idCounter = 1;

    /**
     * Finds a product by its unique ID using Java 8 streams.
     * This method performs a search through the products list and logs warnings
     * when products are not found.
     * 
     * @param id the unique identifier of the product to find
     * @return the Product object with the specified ID, or null if no product 
     *         with the given ID exists
     */
    public Product findProductById(int id) {
        Product p = products.stream()
            .filter(prod -> prod.getId() == id)
            .findFirst()
            .orElse(null);

        if (p == null) {
            logger.warn("Product ID {} not found", id);
        }
        return p;
    }

    /**
     * Adds a new product to the service with comprehensive validation.
     * This method validates all input parameters and creates a new product with
     * an auto-generated unique ID. All validation failures result in logged errors
     * and thrown exceptions.
     * 
     * @param name the name of the product. Must not be null or empty after trimming.
     * @param price the price of the product. Must be non-negative.
     * @param categoryId the ID of the category this product belongs to
     * @param image the product image as a byte array. Can be null.
     * @param category the category name as a string. Must not be null or empty after trimming.
     * @throws IllegalArgumentException if price is negative, name is null/empty, or category is null/empty
     */
    public void addProduct(String name, double price, int categoryId, byte[] image, String category) {
        if (price < 0) {
            logger.error("Attempted to add product with negative price: {}", price);
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (name == null || name.trim().isEmpty()) {
            logger.error("Attempted to add product with empty name.");
            throw new IllegalArgumentException("Name is required.");
        }
        if (category == null || category.trim().isEmpty()) {
            logger.error("Attempted to add product with empty category.");
            throw new IllegalArgumentException("Category is required.");
        }

        Product product = new Product(idCounter++, name, price, categoryId, image, category);
        products.add(product);
        logger.info("Added new product: {} (₮{})", name, price);
    }

    /**
     * Updates an existing product with new values.
     * This method finds the product by ID and updates all its properties with the provided values.
     * The operation is logged for both successful updates and failures.
     * 
     * @param id the unique identifier of the product to update
     * @param name the new name for the product
     * @param price the new price for the product
     * @param categoryId the new category ID for the product
     * @param image the new image for the product as a byte array
     * @param category the new category name for the product
     * @return true if the product was found and updated successfully, false if the product was not found
     */
    public boolean updateProduct(int id, String name, double price, int categoryId, byte[] image, String category) {
        Product p = findProductById(id);
        if (p != null) {
            p.setName(name);
            p.setPrice(price);
            p.setCategoryId(categoryId);
            p.setImage(image);
            p.setCategory(category);
            logger.info("Updated product ID {} with new values.", id);
            return true;
        } else {
            logger.warn("Update failed: Product ID {} not found.", id);
            return false;
        }
    }

    /**
     * Removes a product from the service by its unique ID.
     * This method uses Java 8 removeIf functionality to remove the product
     * and logs the operation result for auditing purposes.
     * 
     * @param id the unique identifier of the product to remove
     * @return true if a product with the specified ID was found and removed, 
     *         false if no product with the given ID exists
     */
    public boolean removeProductById(int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        if (removed) {
            logger.info("Removed product with ID {}", id);
        } else {
            logger.warn("Attempted to remove product with ID {} but it was not found", id);
        }
        return removed;
    }

    /**
     * Lists all products in the service to the logger.
     * This method provides a formatted output of all products with their details
     * including ID, name, price, and category. If no products exist, it logs an
     * appropriate message.
     */
    public void listProducts() {
        if (products.isEmpty()) {
            logger.info("No products available to list.");
            return;
        }

        logger.info("Listing all products:");
        for (Product p : products) {
            logger.info("ID: {}, Name: {}, Price: ₮{}, Category: {}", p.getId(), p.getName(), p.getPrice(), p.getCategory());
        }
    }

    /**
     * Retrieves all products managed by this service.
     * This method returns the actual internal list of products, so modifications
     * to the returned list will affect the service's data.
     * 
     * @return the List containing all products in the service.
     *         Returns an empty list if no products are stored.
     */
    public List<Product> getAllProducts() {
        return products;
    }
}
