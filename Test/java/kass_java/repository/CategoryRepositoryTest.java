package src.Test.java.kass_java.repositorytory;

import kass_java.Model.Category;
import org.junit.jupiter.api.*;
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
        Category c = new Category("Ундаа");
        repo.save(c);

        List<Category> all = repo.findAll();
        assertEquals(1, all.size());
        assertEquals("Ундаа", all.get(0).getName());
    }

    @Test
    void testFindById() {
        Category c = new Category("Амттан");
        repo.save(c);
        Category found = repo.findById(c.getId());

        assertNotNull(found);
        assertEquals("Амттан", found.getName());
    }
}
