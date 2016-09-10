package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClient implements CommandLineRunner {

    @Value("${env.name}") private String env;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ConfigClient.class);
        application.setAdditionalProfiles("dev");
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        assertThat(env).isEqualTo("dev");
    }
}
