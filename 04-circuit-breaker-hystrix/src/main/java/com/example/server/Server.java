package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;

@SpringBootApplication
@ComponentScan(excludeFilters = @Filter(value = SpringBootApplication.class))
@EnableDiscoveryClient
public class Server {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Server.class);
        application.setAdditionalProfiles("server");
        application.run(args);
    }
}
