package com.example.server;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static com.example.server.ProductType.*;
import static java.lang.String.format;

@RestController
public class ProductController {
    private static Log logger = LogFactory.getLog(ProductController.class);
    private static Map<ProductType, AtomicLong> ID_GENERATORS = ImmutableMap.of(
            NORMAL, new AtomicLong(),
            ERROR, new AtomicLong(),
            TIMEOUT, new AtomicLong());
    @Value("${spring.profiles}") private String profile;

    @RequestMapping(value = "/product/{type}")
    public Product product(@PathVariable("type") ProductType type) {
        long id = ID_GENERATORS.get(type).incrementAndGet();
        logger.info(format("get product with type=%s, id=%d", type, id));
        switch (type) {
            case NORMAL:
                return new Product(id, type);
            case ERROR:
                throw new RuntimeException(format("error when getting product with type=%s, id=%d", type, id));
            case TIMEOUT:
                sleep(2000);
                return new Product(id, TIMEOUT);
            default:
                throw new RuntimeException("unknown type " + type);
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
