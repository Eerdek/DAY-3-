package kass_java.repository;

import kass_java.Model.Category;

public class CategoryRepository {
    private int mockIdCounter = 1;

    public int save(Category category) {
        if (category.getId() == 0) {
            System.out.println("NEW CATEGORY: " + category.getName());
            category.setId(mockIdCounter++);
        } else {
            System.out.println("CATEGORY UPDATE: ID=" + category.getId() + ", нэр=" + category.getName());
        }
        return category.getId();
    }
}
