package com.delmur.javapro.yuka.controllers;

import com.delmur.javapro.yuka.models.Basket;
import com.delmur.javapro.yuka.models.dto.BasketDTO;
import com.delmur.javapro.yuka.models.dto.Mapper;
import com.delmur.javapro.yuka.models.dto.ProductDTO;
import com.delmur.javapro.yuka.services.BasketService;
import com.delmur.javapro.yuka.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService;
    private final Mapper mapper;

    @Autowired
    public BasketController(BasketService basketService, Mapper mapper)
    {
        this.basketService = basketService;
        this.mapper = mapper;
    }

    @PostMapping("/")
    public ResponseEntity<BasketDTO> getBasketAverageInformation(@RequestBody Basket basket) {

        try{
            return ResponseEntity.ok(mapper.toDto(basketService.getBasketAverage(basket)));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
