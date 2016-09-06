package com.example.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@RestController
public class Client {
    private static Log logger = LogFactory.getLog(Client.class);
    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Client.class);
        application.setAdditionalProfiles("client");
        application.run(args);
    }

    @RequestMapping("/products/{type}/{count}")
    public List<Product> products(@PathVariable("type") ProductType type, @PathVariable("count") int count) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            logger.info("getting product number " + (i + 1));
            try {
                products.add(productService.product(type));
                logger.info("product received");
            } catch (Exception e) {
                logger.error("error occurred: " + e.getMessage());
            }
        }
        return products;
    }
}
