package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.ProductResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenFoodFactService {
    private String url = "https://fr.openfoodfacts.org/api/v0/produit/%s.json?fields=code,generic_name,product_name,saturated-fat_100g,energy_100g,sugars_100g,salt_100g,fiber_100g,proteins_100g";

    public ProductResult.Product getByBarCode(String barCode) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ProductResult product
                    = restTemplate.getForObject(String.format(url, barCode), ProductResult.class);
            return product.getProduct();
        } catch (Exception e) {
            return null;
        }
    }
}
