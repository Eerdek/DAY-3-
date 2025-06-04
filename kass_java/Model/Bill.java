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
}
