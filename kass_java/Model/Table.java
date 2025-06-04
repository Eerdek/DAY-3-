package kass_java.Model;

public class Table {
      private int id;
    private String name;

    public Table(String name) {
        this.name = name;
    }

    public Table(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }

    // Setters (хэрвээ хэрэгтэй бол)
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}
