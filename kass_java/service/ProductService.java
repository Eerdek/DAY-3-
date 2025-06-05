package kass_java.service;
import java.util.*;

import kass_java.Model.Product;

public class ProductService {
    private List<Product> products = new ArrayList<>();
    private int idCounter = 1;

    public void addProduct(String name, double price, int categoryId, byte[] image, String category) {
        Product product = new Product(idCounter++, name, price, categoryId, image, category);
        products.add(product);
        System.out.println("Add Product: " + product.getName());
    }

    public boolean removeProductById(int id) {
        return products.removeIf(p -> p.getId() == id);
    }

    public void listProducts() {
        System.out.println("\n all Product:");
        for (Product p : products) {
            System.out.println(p.getId() + ". " + p.getName() + " | ₮" + p.getPrice() + " | Category: " + p.getCategory());
        }
    }
}
