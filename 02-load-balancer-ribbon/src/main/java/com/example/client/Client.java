package com.example.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@SpringBootApplication
@EnableDiscoveryClient
public class Client implements CommandLineRunner {
    private static Log logger = LogFactory.getLog(Client.class);

    @Autowired
    private LoadBalancerClient client;
    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Client.class);
        application.setAdditionalProfiles("client");
        application.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("LoadBalancerClient usage...");
        for (int i = 0; i < 10; i++) {
            ServiceInstance serviceInstance = client.choose("server");
            String uri = fromUri(serviceInstance.getUri()).path("name").toUriString();
            String name = new RestTemplate().getForObject(uri, String.class);
            logger.info(name);
        }
        logger.info("Load balanced RestTemplate usage...");
        for (int i = 0; i < 10; i++) {
            String name = restTemplate.getForObject("http://server/name", String.class);
            logger.info(name);
        }
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
