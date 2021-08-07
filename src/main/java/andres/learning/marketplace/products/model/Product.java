package andres.learning.marketplace.products.model;

public class Product {

    private int id;
    private String name;
    private String photoFileName;
    private String description;
    private int price;

    public Product(int id, String name, String photoFileName, String description, int price) {
        this.id = id;
        this.name = name;
        this.photoFileName = photoFileName;
        this.description = description;
        this.price = price;
    }

    public Product(String name, String photoFileName, String description, int price) {
        this.name = name;
        this.photoFileName = photoFileName;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", photoFileName='" + photoFileName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
