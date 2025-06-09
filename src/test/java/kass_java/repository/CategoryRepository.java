package kass_java.repository;

import java.util.ArrayList;
import java.util.List;

import kass_java.Model.Category;

public class CategoryRepository {
    private int mockIdCounter = 1;
    private List<Category> categories = new ArrayList<>();

    public int save(Category category) {
        if (category.getId() == 0) {
            category.setId(mockIdCounter++);
            categories.add(category); // Add new category
            System.out.println("NEW CATEGORY: " + category.getName());
        } else {
            System.out.println("CATEGORY UPDATE: ID=" + category.getId() + ", нэр=" + category.getName());
        }
        return category.getId();
    }

    public List<Category> findAll() {
        return categories;
    }

    public Category findById(int id) {
        return categories.stream()
            .filter(c -> c.getId() == id)
            .findFirst()
            .orElse(null);
    }
}
