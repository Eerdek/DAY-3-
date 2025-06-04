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
}
