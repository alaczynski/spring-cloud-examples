package com.example.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
public class Client implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Client.class);
        application.setAdditionalProfiles("client");
        application.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        String uri = "http://localhost:8001/text-to-upper-case?text=some_text";
        String textInUpperCase = new RestTemplate().getForObject(uri, String.class);
        assertThat(textInUpperCase).isEqualTo("SOME_TEXT");
    }
}
