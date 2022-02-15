package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.ProductResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenFoodFactService {

    private final String url;

    private final RestTemplate restTemplate;

    @Autowired
    public OpenFoodFactService(@Lazy RestTemplate restTemplate, @Value("${environments.dev.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.build();
    }

    public ProductResult.Product getByBarCode(String barCode) {

        try {
            ProductResult product
                    = restTemplate.getForObject(String.format(url, barCode), ProductResult.class);
            if (product != null)
                return product.getProduct();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
