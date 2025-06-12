package kass_java.repository;

import java.util.ArrayList;
import java.util.List;

import kass_java.Model.Table;

/**
 * Mock repository class for providing sample Table data.
 * This class serves as a simple data provider with pre-populated table information
 * for testing and demonstration purposes in the KASS Java POS System.
 * 
 * @author KASS Java POS System
 * @version 1.0
 * @since 2025-06-12
 */
public class TableRep {

    /**
     * In-memory storage for mock tables using ArrayList.
     * This list is pre-populated with sample table data during construction.
     * Contains three predefined tables for demonstration purposes.
     */
    private final List<Table> mockTables = new ArrayList<>();

    /**
     * Constructor that initializes the repository with mock table data.
     * Creates three sample tables with predefined IDs and names:
     * - Table 1: "Table A1"
     * - Table 2: "Table B1" 
     * - Table 3: "Table C1"
     */
    public TableRep() {
        mockTables.add(new Table(1, "Table A1"));
        mockTables.add(new Table(2, "Table B1"));
        mockTables.add(new Table(3, "Table C1"));
    }

    /**
     * Retrieves all available tables from the mock repository.
     * 
     * @return a new ArrayList containing copies of all mock tables.
     *         The returned list is a defensive copy, so modifications won't affect the repository's data.
     *         Always returns exactly 3 tables as defined in the constructor.
     */
    public List<Table> getAllTables() {
        return new ArrayList<>(mockTables);
    }
}
