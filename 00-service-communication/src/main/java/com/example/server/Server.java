package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Server {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Server.class);
        application.setAdditionalProfiles("server");
        application.run(args);
    }

    @RequestMapping("/text-to-upper-case")
    public String textToUpperCase(@RequestParam("text") String text) {
        return text.toUpperCase();
    }
}
