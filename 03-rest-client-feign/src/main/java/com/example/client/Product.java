package com.example.client;

public class Product {
    private String name;
    private String description;

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private Product() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
