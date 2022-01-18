package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenFoodFactService {
    private String url = "https://fr.openfoodfacts.org/api/v0/produit/%s.json?fields=code,generic_name,product_name,saturated-fat_100g,energy_100g,sugars_100g,salt_100g,fiber_100g,proteins_100g";

    public Product getByBarCode(String barCode) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response
                = restTemplate.getForEntity(String.format(url, barCode), String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(response.getBody());

        if(root.findPath("status_verbose") != null &&
                root.findPath("status_verbose").asText().equals("product not found")) {
            throw new Exception("Product does not exist");
        }

        return new Product(root);
    }
}
