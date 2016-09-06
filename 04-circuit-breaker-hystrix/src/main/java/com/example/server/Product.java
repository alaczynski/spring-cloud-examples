package com.example.server;

public class Product {
    private final long id;
    private final ProductType type;

    public Product(long id, ProductType type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public ProductType getType() {
        return type;
    }
}
