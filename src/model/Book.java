package model;

import java.math.BigDecimal;

public class Book extends Product {

    private Author author;

    public Book(String name, BigDecimal amount, String category, String description, int stock, Publisher publisher, Author author) {
        super(name, amount, category, description, stock, publisher);
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
