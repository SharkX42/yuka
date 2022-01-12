package com.delmur.javapro.yuka.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping("/all")
    public ResponseEntity getResourceGroupTotalCost() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://fr.openfoodfacts.org/api/v0/produit/7622210449283.json";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(response.getBody());

        JsonNode product = root.path("product");

        String product_name = product.path("product_name").toString();



        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        return ResponseEntity.ok("test");
    }

}
