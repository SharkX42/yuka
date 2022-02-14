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
    /* TODO : Poser question pour savoir si c'est viable */
//    @Value("${environments.dev.url}")
//    private String url;

    private String url = "https://fr.openfoodfacts.org/api/v0/produit/%s.json?fields=code,generic_name,product_name,saturated-fat_100g,energy_100g,sugars_100g,salt_100g,fiber_100g,proteins_100g";

    private final RestTemplate restTemplate;

    @Autowired
    public OpenFoodFactService(@Lazy RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
            return product.getProduct();
        } catch (Exception e) {
            return null;
        }
    }
}
