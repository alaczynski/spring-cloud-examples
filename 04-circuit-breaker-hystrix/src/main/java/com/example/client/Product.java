package com.example.client;

public class Product {
    private long id;
    private String description;

    public Product(long id, String description) {
        this.id = id;
        this.description = description;
    }

    private Product() {
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
