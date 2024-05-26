package model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private String category;
    private String description;
    private int stock;
    private Publisher publisher;

    public Product(String name, BigDecimal price, String category, String description, int stock, Publisher publisher) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.stock = stock;
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", publisher=" + publisher +
                '}';
    }
}
