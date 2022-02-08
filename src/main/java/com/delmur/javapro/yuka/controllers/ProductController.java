package com.delmur.javapro.yuka.controllers;

import com.delmur.javapro.yuka.models.dto.Mapper;
import com.delmur.javapro.yuka.models.dto.ProductDTO;
import com.delmur.javapro.yuka.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;
    private final Mapper mapper;

    @Autowired
    public ProductController(ProductService service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{barCode}")
    public ResponseEntity<ProductDTO> getProductByBarCode(@PathVariable String barCode) {

        try{
            return ResponseEntity.ok(mapper.toDto(service.getProductByBarCode(barCode)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
