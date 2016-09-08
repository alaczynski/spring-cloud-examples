package com.example.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;

import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@SpringBootApplication
@EnableDiscoveryClient
public class Client implements CommandLineRunner {
    private static Log logger = LogFactory.getLog(Client.class);

    @Autowired
    private LoadBalancerClient client;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Client.class);
        application.setAdditionalProfiles("client");
        application.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        for (int i = 0; i < 100; i++) {
            ServiceInstance serviceInstance = client.choose("server");
            String uri = fromUri(serviceInstance.getUri()).path("name").toUriString();
            String name = new RestTemplate().getForObject(uri, String.class);
            logger.info(name);
        }
    }
}
