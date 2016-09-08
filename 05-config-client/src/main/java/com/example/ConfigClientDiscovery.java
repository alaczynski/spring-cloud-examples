package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(excludeFilters = @ComponentScan.Filter(value = SpringBootApplication.class))
public class ConfigClientDiscovery implements CommandLineRunner {

    @Value("${env.name}") private String env;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ConfigClientDiscovery.class);
        application.setAdditionalProfiles("dev");
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        assertThat(env).isEqualTo("dev");
    }
}
