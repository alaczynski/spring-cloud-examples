package com.example.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@SpringBootApplication
@EnableDiscoveryClient
public class Client implements CommandLineRunner {
    private static Log logger = LogFactory.getLog(Client.class);

    @Autowired private DiscoveryClient client;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Client.class);
        application.setAdditionalProfiles("client");
        application.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("services: " + client.getServices());
        logger.info("server instances: " + client.getInstances("server"));

        ServiceInstance serviceInstance = client.getInstances("server").get(0);
        String uri = fromUri(serviceInstance.getUri()).path("text-to-upper-case")
                .queryParam("text", "some_text")
                .toUriString();
        String textInUpperCase = new RestTemplate().getForObject(uri, String.class);
        assertThat(textInUpperCase).isEqualTo("SOME_TEXT");
    }
}
