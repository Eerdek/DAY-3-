package kass_java.service;

import org.junit.jupiter.api.*;

import kass_java.Model.Product;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    ProductService service;

    @BeforeEach
    void setUp() {
        service = new ProductService();
    }

    @Test
    void testAddProduct() {
        service.addProduct("Cola", 2500.0, 1, null, "Ундаа");
        List<Product> products = service.getAllProducts();

        assertEquals(1, products.size());
        assertEquals("Cola", products.get(0).getName());
    }

    @Test
    void testRemoveProductById() {
        service.addProduct("Fanta", 2000.0, 2, null, "Ундаа");
        int id = service.getAllProducts().get(0).getId();

        boolean result = service.removeProductById(id);
        assertTrue(result);
        assertEquals(0, service.getAllProducts().size());
    }

    @Test
    void testRemoveNonExistingProduct() {
        boolean result = service.removeProductById(9999);
        assertFalse(result);
    }
}
