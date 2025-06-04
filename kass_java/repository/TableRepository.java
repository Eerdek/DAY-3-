package kass_java.repository;

import kass_java.Model.Table;

import java.util.ArrayList;
import java.util.List;

public class TableRepository {

    private final List<Table> tableList = new ArrayList<>();
    private int currentId = 1;

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

    public Table getById(int id) {
        for (Table table : tableList) {
            if (table.getId() == id) {
                return table;
            }
        }
        return null;
    }
    
    public Table getByName(String name) {
        for (Table table : tableList) {
            if (table.getName().equalsIgnoreCase(name)) {
                return table;
            }
        }
        return null;
    }
    
    
    public List<Table> getAllTables() {
        return new ArrayList<>(tableList);
    }
    
    
    public List<Table> getOccupiedTables() {
        List<Table> occupied = new ArrayList<>();
        for (Table table : tableList) {
            if (table.getStatus().equals("Occupied")) {
                occupied.add(table);
            }
        }
        return occupied;
    }
    
    
    public List<Table> getAvailableTables() {
        List<Table> available = new ArrayList<>();
        for (Table table : tableList) {
            if (table.getStatus().equals("Available")) {
                available.add(table);
            }
        }
        return available;
    }
    
    
    public boolean updateTableStatus(String tableName, int orderId, String newStatus) {
        Table table = getByName(tableName);
        if (table != null) {
            table.setStatus(newStatus);
            if (newStatus.equals("Occupied")) {
                table.setCurrentOrderId(orderId);
            } else if (newStatus.equals("Available")) {
                table.setCurrentOrderId(0);
            }
            return save(table) == 1;
        }
        return false;
    }
}
