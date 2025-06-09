package kass_java.service;

import java.util.*;
import kass_java.Model.Product;

public class ProductService {
    private List<Product> products = new ArrayList<>();
    private int idCounter = 1;

    public Product findProductById(int id) {
        return products.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public void addProduct(String name, double price, int categoryId, byte[] image, String category) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name is required.");
        if (category == null || category.trim().isEmpty()) throw new IllegalArgumentException("Category is required.");

        Product product = new Product(idCounter++, name, price, categoryId, image, category);
        products.add(product);
    }

    public boolean updateProduct(int id, String name, double price, int categoryId, byte[] image, String category) {
        Product p = findProductById(id);
        if (p != null) {
            p.setName(name);
            p.setPrice(price);
            p.setCategoryId(categoryId);
            p.setImage(image);
            p.setCategory(category);
            return true;
        }
        return false;
    }

    public boolean removeProductById(int id) {
        return products.removeIf(p -> p.getId() == id);
    }

    public void listProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.printf("\n%-4s %-20s %-10s %-15s\n", "ID", "Name", "Price", "Category");
        System.out.println("-----------------------------------------------------");

        for (Product p : products) {
            System.out.printf("%-4d %-20s ₮%-9.2f %-15s\n", p.getId(), p.getName(), p.getPrice(), p.getCategory());
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
