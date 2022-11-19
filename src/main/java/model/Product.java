package model;

public class Product {
    private String ProductName;
    private int price;

    public Product(String productName, int price) {
        ProductName = productName;
        this.price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getPrice() {
        return price;
    }

    public void incrementPrice(int price) {
        this.price += price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductName='" + ProductName + '\'' +
                ", price=" + price +
                '}';
    }
}
