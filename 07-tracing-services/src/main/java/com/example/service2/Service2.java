package com.example.service2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.SpanAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Service2 {
    private static final Log LOG = LogFactory.getLog(MethodHandles.lookup().lookupClass());
    @Autowired private SpanAccessor spanAccessor;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Service2.class);
        application.setAdditionalProfiles("service-2");
        application.run(args);
    }

    @RequestMapping("/hello")
    public String hello() {
        LOG.info("hello");
        LOG.info("span: " + spanAccessor.getCurrentSpan());
        return "service-2";
    }
}
