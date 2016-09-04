package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer1 {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EurekaServer1.class);
        application.setAdditionalProfiles("eureka-server-1");
        application.run(args);
    }
}
