package kass_java.repository;

import kass_java.Model.Product;

import java.util.*;

public class ProductRepository {

    private final List<Product> mockProducts = new ArrayList<>();
    private int productIdCounter = 1;

    public int save(Product product) {
        if (product.getId() == 0) {
            product.setId(productIdCounter++);
            mockProducts.add(product);
            System.out.println("NEW PRODUCT: " + product.getName());
        } else {
            for (int i = 0; i < mockProducts.size(); i++) {
                if (mockProducts.get(i).getId() == product.getId()) {
                    mockProducts.set(i, product);
                    System.out.println("UPDATE PRODUCT: " + product.getName());
                    break;
                }
            }
        }
        return 1;
    }

    public Product getProductById(int id) {
        for (Product p : mockProducts) {
            if (p.getId() == id) {
                return p;
            }
        }
        System.out.println("NOT FOUND PRODUCT ID: " + id);
        return null;
    }
}
