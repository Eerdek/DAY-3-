package kass_java.repository;

import org.junit.jupiter.api.*;

import kass_java.Model.Category;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest {

    CategoryRepository repo;

    @BeforeEach
    void setup() {
        repo = new CategoryRepository();
    }

    @Test
    void testSaveCategory() {
        Category c = new Category("DRINK");
        repo.save(c);

        List<Category> all = repo.findAll();
        assertEquals(1, all.size());
        assertEquals("DRINK", all.get(0).getName());
    }

    @Test
    void testFindById() {
        Category c = new Category("DESERT");
        repo.save(c);
        Category found = repo.findById(c.getId());

        assertNotNull(found);
        assertEquals("DESERT", found.getName());
    }
}
