package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinServer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ZipkinServer.class);
        application.setAdditionalProfiles("zipkin");
        application.run(args);
    }
}
