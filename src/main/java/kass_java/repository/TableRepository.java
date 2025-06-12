package kass_java.repository;

import java.util.ArrayList;
import java.util.List;
import kass_java.Model.Table;

/**
 * Repository class for managing Table entities in memory.
 * This class provides CRUD operations for tables using an in-memory ArrayList
 * as the data store with automatic ID generation for new tables.
 * 
 * @author KASS Java POS System
 * @version 1.0
 * @since 2025-06-12
 */
public class TableRepository {

    /**
     * In-memory storage for tables using ArrayList.
     * This list maintains all tables that have been saved to the repository.
     */
    private final List<Table> tableList = new ArrayList<>();
    
    /**
     * Counter for generating unique IDs for new tables.
     * Starts at 1 and increments for each new table saved.
     */
    private int currentId = 1;

    /**
     * Saves a table to the repository.
     * If the table has an ID of 0, it's treated as a new table and assigned a new unique ID.
     * If the table already has an ID, it updates the existing table in the repository.
     * 
     * @param table the Table object to save. Must not be null.
     * @return 1 if the operation was successful (either save or update), 0 if update failed (table not found)
     * @throws NullPointerException if table is null
     */
    public int save(Table table) {
        if (table.getId() == 0) {
            table.setId(currentId++);
            tableList.add(table);
            return 1;
        } else {
            for (int i = 0; i < tableList.size(); i++) {
                if (tableList.get(i).getId() == table.getId()) {
                    tableList.set(i, table);
                    return 1;
                }
            }
            return 0;
        }
    }

    /**
     * Retrieves a table by its unique ID using iterative search.
     * This method performs a linear search through the tables list.
     * 
     * @param id the unique identifier of the table to find
     * @return the Table object with the specified ID, or null if no table 
     *         with the given ID exists in the repository
     */
    public Table getById(int id) {
        for (Table table : tableList) {
            if (table.getId() == id) {
                return table;
            }
        }
        return null;
    }

    /**
     * Retrieves all tables from the repository.
     * 
     * @return the actual internal list containing all tables in the repository.
     *         Returns an empty list if no tables are stored.
     *         Note: The returned list is the actual internal list, so modifications will affect the repository's data.
     */
    public List<Table> findAll() {
        return tableList;
    }

    /**
     * Finds a table by its unique ID.
     * This method delegates to the getById method for table retrieval.
     * 
     * @param id the unique identifier of the table to find
     * @return the Table object with the specified ID, or null if no table 
     *         with the given ID exists in the repository
     */
    public Table findById(int id) {
        return getById(id);
    }
}
