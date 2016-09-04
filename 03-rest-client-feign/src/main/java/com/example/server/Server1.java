package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;

@SpringBootApplication
@ComponentScan(excludeFilters = @Filter(value = SpringBootApplication.class))
@EnableDiscoveryClient
public class Server1 {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Server1.class);
        application.setAdditionalProfiles("server-1");
        application.run(args);
    }
}
