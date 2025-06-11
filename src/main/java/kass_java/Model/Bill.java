package kass_java.Model;

public class Bill {

    private int mainID;
    private String tableName;
    private String waiterName;
    private String orderType;
    private String status;
    private double total;

    public Bill(int mainID, String tableName, String waiterName, String orderType, String status, double total) {
        this.mainID = mainID;
        this.tableName = tableName;
        this.waiterName = waiterName;
        this.orderType = orderType;
        this.status = status;
        this.total = total;
    }

    public int getMainID() {
        return mainID;
    }

    public void setMainID(int mainID) {
        this.mainID = mainID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
