package kass_java.repository;

import java.util.ArrayList;
import java.util.List;
import kass_java.Model.Table;

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

    public List<Table> findAll() {
        return tableList;
    }

    public Table findById(int id) {
        return getById(id);
    }
}
