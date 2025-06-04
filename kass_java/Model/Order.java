package kass_java.Model;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Date date;
    private String time;
    private String tableName;
    private String waiterName;
    private String status;
    private String orderType;
    private double total;
    private double received;
    private double change;
    private int driverID;
    private String customerName;
    private String customerPhone;
    private List<OrderItem> items;

    public Order(Date date, String time, String tableName, String waiterName,
                 String orderType, double total, double received, double change,
                 int driverID, String customerName, String customerPhone, List<OrderItem> items) {
        this.date = date;
        this.time = time;
        this.tableName = tableName;
        this.waiterName = waiterName;
        this.orderType = orderType;
        this.total = total;
        this.received = received;
        this.change = change;
        this.driverID = driverID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.items = items;
    }

    public Order(int id, Date date, String time, String tableName, String waiterName,
                 String orderType, double total, double received, double change,
                 int driverID, String customerName, String customerPhone, List<OrderItem> items) {
        this(date, time, tableName, waiterName, orderType, total, received, change,
                driverID, customerName, customerPhone, items);
        this.id = id;
    }

    // Getters
    public int getId() { return id; }
    public Date getDate() { return date; }
    public String getTime() { return time; }
    public String getTableName() { return tableName; }
    public String getWaiterName() { return waiterName; }
    public String getOrderType() { return orderType; }
    public double getTotal() { return total; }
    public double getReceived() { return received; }
    public double getChange() { return change; }
    public int getDriverID() { return driverID; }
    public String getCustomerName() { return customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public List<OrderItem> getItems() { return items; }
    public String getStatus() { return status; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public void setStatus(String status) { this.status = status; }
}
