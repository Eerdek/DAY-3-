package kass_java.Model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int categoryId;
    private byte[] image;
    private String category;

    public Product(String name, double price, int categoryId, byte[] image, String category) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.image = image;
        this.category = category;
    }

    public Product(int id, String name, double price, int categoryId, byte[] image, String category) {
       this(name, price, categoryId, image, category);
       this.id = id;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getCategoryId() { return categoryId; }
    public byte[] getImage() { return image; }

    
    public void setId(int id) { this.id = id; }
}
