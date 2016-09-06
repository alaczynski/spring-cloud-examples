package com.example.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("server")
public interface ProductService {

    @RequestMapping(value = "/product/{type}")
    Product product(@PathVariable("type") ProductType type);
}
