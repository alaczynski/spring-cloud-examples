package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class Client {

    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Client.class);
        application.setAdditionalProfiles("client");
        application.run(args);
    }

    @RequestMapping("/products")
    public List<Product> run() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.addAll(productService.products());
        }
        return products;
    }
}
