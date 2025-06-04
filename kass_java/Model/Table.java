package kass_java.Model;

public class Table {
    private int id;
    private String name;
    private String status;
    private int currentOrderId;

    public Table(String name) {
        this.name = name;
        this.status = "Available";
        this.currentOrderId = 0;
    }

    public Table(int id, String name) {
        this.id = id;
        this.name = name;
        this.status = "Available";
        this.currentOrderId = 0;
    }

    public Table(int id, String name, String status, int currentOrderId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.currentOrderId = currentOrderId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getCurrentOrderId() {
        return currentOrderId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurrentOrderId(int currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

    public void assignOrder(int orderId) {
        this.currentOrderId = orderId;
        this.status = "Occupied";
    }

    public void releaseTable() {
        this.currentOrderId = 0;
        this.status = "Available";
    }
}
