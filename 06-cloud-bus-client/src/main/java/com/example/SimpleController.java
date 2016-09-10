package com.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RefreshScope
public class SimpleController {
    private static final Log LOG = LogFactory.getLog(MethodHandles.lookup().lookupClass());
    private static final AtomicInteger COUNTER = new AtomicInteger();
    @Value("${env.name}") private String env;

    public SimpleController() {
        LOG.info(String.format("instance number: %d created", COUNTER.incrementAndGet()));
    }

    @RequestMapping("/controller-status")
    public String property() {
        return String.format("instance number: %s, env.name: %s", COUNTER.get(), env);
    }
}
