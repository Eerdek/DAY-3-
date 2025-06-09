package kass_java.repository;

import java.util.*;
import kass_java.Model.Product;
import kass_java.Model.Product;

public class ProductRepository {

    private final List<Product> products = new ArrayList<>();
    private int productIdCounter = 1;

    public int save(Product product) {
        if (product.getId() == 0) {
            product.setId(productIdCounter++);
            products.add(product);
            System.out.println("NEW PRODUCT: " + product.getName());
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == product.getId()) {
                    products.set(i, product);
                    System.out.println("UPDATE PRODUCT: " + product.getName());
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
        System.out.println("NOT FOUND PRODUCT ID: " + id);
        return null;
    }

    // ✅ Consistent getters using the same list
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
