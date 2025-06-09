package kass_java.repository;

import java.util.ArrayList;
import java.util.List;

import kass_java.Model.Table;

public class TableRep {

    private final List<Table> mockTables = new ArrayList<>();

    public TableRep() {
        // Жишээ хүснэгтүүд
        mockTables.add(new Table(1, "Table A1"));
        mockTables.add(new Table(2, "Table B1"));
        mockTables.add(new Table(3, "Table C1"));
    }

    public List<Table> getAllTables() {
        return new ArrayList<>(mockTables);
    }
}
