package com.example.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Value("${spring.profiles}") String profile;

    @RequestMapping(value = "/products")
    public List<Product> products() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("product-1", profile));
        products.add(new Product("product-2", profile));
        return products;
    }
}
