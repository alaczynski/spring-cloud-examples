package com.example.server2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer2 {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EurekaServer2.class);
        application.setAdditionalProfiles("eureka-server-2");
        application.run(args);
    }
}
