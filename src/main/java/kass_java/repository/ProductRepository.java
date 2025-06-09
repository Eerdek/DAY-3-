package kass_java.repository;

import java.util.*;
import kass_java.Model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductRepository {

     private static final Logger logger = LogManager.getLogger(ProductRepository.class);

    private final List<Product> products = new ArrayList<>();
    private int productIdCounter = 1;

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

    public Product getProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        logger.error("NOT FOUND PRODUCT ID: {}", id);
        return null;
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
