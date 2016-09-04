package com.example.server1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Server1 {

    @Value("${spring.profiles}") private String name;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Server1.class);
        application.setAdditionalProfiles("server-1");
        application.run(args);
    }

    @RequestMapping("/name")
    public String text() {
        return name;
    }
}
