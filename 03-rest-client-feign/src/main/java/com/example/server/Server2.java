package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(value = SpringBootApplication.class))
@EnableDiscoveryClient
public class Server2 {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Server2.class);
        application.setAdditionalProfiles("server-2");
        application.run(args);
    }
}
