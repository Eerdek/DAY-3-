package kass_java.repository;

import kass_java.repository.TableRepository;
import kass_java.Model.Table;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableRepositoryTest {

    TableRepository repo;

    @BeforeEach
    void setup() {
        repo = new TableRepository();
    }

    @Test
    void testSaveTable() {
        Table t = new Table("Ширээ A1");
        repo.save(t);

        List<Table> all = repo.findAll();
        assertEquals(1, all.size());
        assertEquals("Ширээ A1", all.get(0).getName());
    }

    @Test
    void testFindById() {
        Table t = new Table("Ширээ B2");
        repo.save(t);
        Table found = repo.findById(t.getId());

        assertNotNull(found);
        assertEquals("Ширээ B2", found.getName());
    }
}
