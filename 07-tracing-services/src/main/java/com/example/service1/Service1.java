
package com.example.service1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;

import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Service1 {
    private static final Log LOG = LogFactory.getLog(MethodHandles.lookup().lookupClass());
    @Autowired private DiscoveryClient client;
    @Autowired private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Service1.class);
        application.setAdditionalProfiles("service-1");
        application.run(args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/hello")
    public String hello() {
        LOG.info("hello");
        return "service-1" + hello("service-2");
    }

    private String hello(String service) {
        ServiceInstance serviceInstance = client.getInstances(service).get(0);
        String uri = fromUri(serviceInstance.getUri()).path("hello").toUriString();
        return restTemplate.getForObject(uri, String.class);
    }
}
