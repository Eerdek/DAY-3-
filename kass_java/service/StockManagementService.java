package kass_java.service;

import kass_java.Model.Product;
import kass_java.repository.ProductRepository;

import java.util.*;

/**
 * Service for managing product stock levels
 */
public class StockManagementService {
    private final ProductRepository productRepository;
    private int lowStockThreshold = 5; // Default threshold
    
    public StockManagementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    /**
     * Set the threshold for low stock alerts
     */
    public void setLowStockThreshold(int threshold) {
        if (threshold >= 0) {
            this.lowStockThreshold = threshold;
        }
    }
    
    /**
     * Update stock quantity for a product
     */
    public boolean updateStockQuantity(int productId, int newQuantity) {
        if (newQuantity < 0) {
            return false;
        }
        
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            return false;
        }
        
        product.setStockQuantity(newQuantity);
        productRepository.save(product);
        
        return true;
    }
    
    /**
     * Add stock to a product
     */
    public boolean addStock(int productId, int quantityToAdd) {
        if (quantityToAdd <= 0) {
            return false;
        }
        
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            return false;
        }
        
        int newQuantity = product.getStockQuantity() + quantityToAdd;
        product.setStockQuantity(newQuantity);
        productRepository.save(product);
        
        return true;
    }
    
    /**
     * Get products with low stock (below threshold)
     */
    public List<Product> getLowStockProducts() {
        List<Product> allProducts = getAllProducts();
        List<Product> lowStockProducts = new ArrayList<>();
        
        for (Product product : allProducts) {
            if (product.getStockQuantity() <= lowStockThreshold) {
                lowStockProducts.add(product);
            }
        }
        
        return lowStockProducts;
    }
    
    /**
     * Get products that are out of stock
     */
    public List<Product> getOutOfStockProducts() {
        List<Product> allProducts = getAllProducts();
        List<Product> outOfStockProducts = new ArrayList<>();
        
        for (Product product : allProducts) {
            if (product.getStockQuantity() == 0) {
                outOfStockProducts.add(product);
            }
        }
        
        return outOfStockProducts;
    }
    
    /**
     * Generate low stock alert message
     */
    public String generateLowStockAlertMessage() {
        List<Product> lowStockProducts = getLowStockProducts();
        
        if (lowStockProducts.isEmpty()) {
            return "All products have sufficient stock levels.";
        }
        
        StringBuilder alertMessage = new StringBuilder("Low Stock Alert:\n");
        for (Product product : lowStockProducts) {
            alertMessage.append("- ")
                .append(product.getName())
                .append(": ")
                .append(product.getStockQuantity())
                .append(" remaining (Threshold: ")
                .append(lowStockThreshold)
                .append(")\n");
        }
        
        return alertMessage.toString();
    }
    
    /**
     * Helper method to get all products from repository
     */
    private List<Product> getAllProducts() {
        // This is a simplified implementation as we don't have a repository method for this
        // In a real implementation, you would call productRepository.getAllProducts()
        List<Product> products = new ArrayList<>();
        
        // For now, we'll use a dummy approach to get products since the mock repository
        // doesn't have a getAllProducts method
        for (int i = 1; i < 100; i++) {  // Assuming product IDs are sequential
            Product product = productRepository.getProductById(i);
            if (product != null) {
                products.add(product);
            }
        }
        
        return products;
    }
}
