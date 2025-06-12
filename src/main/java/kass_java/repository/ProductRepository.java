package kass_java.repository;

import java.util.*;
import kass_java.Model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Repository class for managing Product entities in memory.
 * This class provides CRUD operations for products using an in-memory ArrayList
 * as the data store with automatic ID generation for new products.
 * 
 * @author KASS Java POS System
 * @version 1.0
 * @since 2025-06-12
 */
public class ProductRepository {

     private static final Logger logger = LogManager.getLogger(ProductRepository.class);

    /**
     * In-memory storage for products using ArrayList.
     * This list maintains all products that have been saved to the repository.
     */
    private final List<Product> products = new ArrayList<>();
    
    /**
     * Counter for generating unique IDs for new products.
     * Starts at 1 and increments for each new product saved.
     */
    private int productIdCounter = 1;

    /**
     * Saves a product to the repository.
     * If the product has an ID of 0, it's treated as a new product and assigned a new unique ID.
     * If the product already has an ID, it updates the existing product in the repository.
     * 
     * @param product the Product object to save. Must not be null.
     * @return always returns 1 to indicate successful operation
     * @throws NullPointerException if product is null
     */
    public int save(Product product) {
        if (product.getId() == 0) {
            product.setId(productIdCounter++);
            products.add(product);
            logger.info("NEW PRODUCT: {}", product.getName());
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == product.getId()) {
                    products.set(i, product);
                    logger.info("UPDATE PRODUCT: {}", product.getName());
                    break;
                }
            }
        }
        return 1;
    }

    /**
     * Retrieves a product by its unique ID using iterative search.
     * This method performs a linear search through the products list.
     * 
     * @param id the unique identifier of the product to find
     * @return the Product object with the specified ID, or null if no product 
     *         with the given ID exists in the repository
     */
    public Product getProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        logger.error("NOT FOUND PRODUCT ID: {}", id);
        return null;
    }

    /**
     * Retrieves all products from the repository.
     * 
     * @return a new ArrayList containing copies of all products in the repository.
     *         Returns an empty list if no products are stored.
     *         The returned list is a defensive copy, so modifications won't affect the repository's data.
     */
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    /**
     * Finds a product by its unique ID using Java 8 streams.
     * This method provides an alternative way to search for products using functional programming.
     * 
     * @param id the unique identifier of the product to find
     * @return the Product object with the specified ID, or null if no product 
     *         with the given ID exists in the repository
     */
    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
