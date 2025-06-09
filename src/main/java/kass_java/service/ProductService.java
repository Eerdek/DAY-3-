package kass_java.service;

import java.util.*;
import kass_java.Model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    private List<Product> products = new ArrayList<>();
    private int idCounter = 1;

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

    public boolean removeProductById(int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        if (removed) {
            logger.info("Removed product with ID {}", id);
        } else {
            logger.warn("Attempted to remove product with ID {} but it was not found", id);
        }
        return removed;
    }

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

    public List<Product> getAllProducts() {
        return products;
    }
}
