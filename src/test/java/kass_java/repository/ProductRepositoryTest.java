package kass_java.repository;

import kass_java.repository.ProductRepository;

import org.junit.jupiter.api.*;

import kass_java.Model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository repo;

    @BeforeEach
    void setup() {
        repo = new ProductRepository();
    }

    @Test
    void testSaveProduct() {
        Product p = new Product("Sprite", 1800.0, 1, null, "Ундаа");
        repo.save(p);

        List<Product> all = repo.findAll();
        assertEquals(1, all.size());
        assertEquals("Sprite", all.get(0).getName());
    }

    @Test
    void testFindById() {
        Product p = new Product("Coca Cola", 2000.0, 2, null, "Ундаа");
        repo.save(p);

        Product found = repo.findById(p.getId());
        assertNotNull(found);
        assertEquals("Coca Cola", found.getName());
    }
}
