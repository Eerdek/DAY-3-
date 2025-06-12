package kass_java.repository;

import java.util.ArrayList;
import java.util.List;
import kass_java.Model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Repository class for managing Category entities in memory.
 * This class provides CRUD operations for categories using an in-memory ArrayList
 * as the data store with mock ID generation.
 * 
 * @author KASS Java POS System
 * @version 1.0
 * @since 2025-06-12
 */
public class CategoryRepository {

    private static final Logger logger = LogManager.getLogger(CategoryRepository.class);

    /**
     * Counter for generating unique mock IDs for new categories.
     * Starts at 1 and increments for each new category saved.
     */
    private int mockIdCounter = 1;
    
    /**
     * In-memory storage for categories using ArrayList.
     * This list maintains all categories that have been saved to the repository.
     */
    private final List<Category> categories = new ArrayList<>();

    /**
     * Saves a category to the repository. 
     * If the category has an ID of 0, it's treated as a new category and assigned a new unique ID.
     * If the category already has an ID, it's treated as an update operation (logging only).
     * 
     * @param category the Category object to save. Must not be null.
     * @return the ID of the saved category (newly assigned for new categories, existing ID for updates)
     * @throws NullPointerException if category is null
     */
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

    /**
     * Retrieves all categories from the repository.
     * 
     * @return a List containing all categories in the repository. 
     *         Returns an empty list if no categories are stored.
     *         The returned list is the actual internal list, so modifications
     *         will affect the repository's data.
     */
    public List<Category> findAll() {
        logger.info("Retrieving all categories ({} total)", categories.size());
        return categories;
    }

    /**
     * Finds a category by its unique ID.
     * 
     * @param id the unique identifier of the category to find
     * @return the Category object with the specified ID, or null if no category 
     *         with the given ID exists in the repository
     */
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
