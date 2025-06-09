package kass_java.repository;

import java.util.ArrayList;
import java.util.List;
import kass_java.Model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoryRepository {

    private static final Logger logger = LogManager.getLogger(CategoryRepository.class);

    private int mockIdCounter = 1;
    private final List<Category> categories = new ArrayList<>();

    public int save(Category category) {
        if (category.getId() == 0) {
            category.setId(mockIdCounter++);
            categories.add(category);
            logger.info("NEW CATEGORY added: {}", category.getName());
        } else {
            logger.info("CATEGORY UPDATED: ID={}, Name={}", category.getId(), category.getName());
        }
        return category.getId();
    }

    public List<Category> findAll() {
        logger.info("Retrieving all categories ({} total)", categories.size());
        return categories;
    }

    public Category findById(int id) {
        Category result = categories.stream()
            .filter(c -> c.getId() == id)
            .findFirst()
            .orElse(null);

        if (result == null) {
            logger.warn("Category not found for ID={}", id);
        } else {
            logger.info("Category found: ID={}, Name={}", result.getId(), result.getName());
        }

        return result;
    }
}
