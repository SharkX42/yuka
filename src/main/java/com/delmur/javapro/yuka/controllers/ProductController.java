package com.delmur.javapro.yuka.controllers;

import com.delmur.javapro.yuka.models.Product;
import com.delmur.javapro.yuka.services.OpenFoodFactService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final OpenFoodFactService service;

    @Autowired
    public ProductController(OpenFoodFactService service) {
        this.service = service;
    }

    @GetMapping("/{barCode}")
    public ResponseEntity<Product> getProductByBarCode(@PathVariable String barCode) {

        try{
            Product p = service.getByBarCode(barCode);
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

}
