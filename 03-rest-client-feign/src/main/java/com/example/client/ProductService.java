package com.example.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("server")
public interface ProductService {

    @RequestMapping(value = "/products")
    List<Product> products();
}
