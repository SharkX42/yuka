package com.delmur.javapro.yuka.controllers;

import com.delmur.javapro.yuka.models.NutriScore;
import com.delmur.javapro.yuka.models.ProductResult;
import com.delmur.javapro.yuka.models.dto.Mapper;
import com.delmur.javapro.yuka.models.dto.ProductDTO;
import com.delmur.javapro.yuka.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService service;

    @MockBean
    private Mapper mapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getProductShouldReturnResource() throws Exception {
        Mockito.when(service.getProductByBarCode(Mockito.anyString())).thenReturn(
                new ProductResult.Product("7622210449283",
                        1962,
                        4,
                        "BISCUITS FOURRÉS (35%) PARFUM CHOCOLAT",
                        "Prince Chocolat",
                        6.3,
                        0.49,
                        5.6,
                        32,
                        10,
                        new NutriScore(3, "Mangeable", 3, 10, "yellow"))
        );

        Mockito.when(mapper.toDto(Mockito.any(ProductResult.Product.class))).thenReturn(
                new ProductDTO(0,"7622210449283", "BISCUITS FOURRÉS (35%) PARFUM CHOCOLAT - Prince Chocolat", 10, "Mangeable" , "yellow"));

        mockMvc.perform(get("/api/product/7622210449283"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.barCode", is("7622210449283")))
                .andExpect(jsonPath("$.classe", is("Mangeable")));
    }

    @Test
    public void getProductShouldReturn404WhenProductDoesNotExist() throws Exception {
        Mockito.when(service.getProductByBarCode(Mockito.anyString())).thenReturn(null);
        mockMvc.perform(get("/api/product/7622210449282"))
                .andExpect(status().isNotFound());


    }

}
