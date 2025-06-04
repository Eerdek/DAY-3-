package kass_java.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddCustomerForm {
    private String orderType = "";
    private int driverID = 0;
    private String cusName = "";
    private int mainID = 0;

    public AddCustomerForm(String orderType, int driverID, String cusName, int mainID) {
        this.orderType = orderType;
        this.driverID = driverID;
        this.cusName = cusName;
        this.mainID = mainID;
    }

    public List<Driver> getDriverList() {
        List<Driver> drivers = new ArrayList<>();
        if (!orderType.equals("Take Away")) {

            drivers.add(new Driver(1, "Bat-Erdene"));
            drivers.add(new Driver(2, "Tuvshinbold"));
            drivers.add(new Driver(3, "Anujin"));
        }
        return drivers;
    }

    public int getDriverIDByIndex(int index, List<Driver> drivers) {
        if (index >= 0 && index < drivers.size()) {
            driverID = drivers.get(index).getId();
            return driverID;
        }
        return -1;
    }
}