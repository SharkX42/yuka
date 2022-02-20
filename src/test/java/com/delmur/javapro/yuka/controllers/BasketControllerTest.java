package com.delmur.javapro.yuka.controllers;

import com.delmur.javapro.yuka.exceptions.BasketEmptyException;
import com.delmur.javapro.yuka.models.Basket;
import com.delmur.javapro.yuka.models.BasketAverage;
import com.delmur.javapro.yuka.models.NutriScore;
import com.delmur.javapro.yuka.models.ProductResult;
import com.delmur.javapro.yuka.models.dto.BasketDTO;
import com.delmur.javapro.yuka.models.dto.Mapper;
import com.delmur.javapro.yuka.models.dto.ProductDTO;
import com.delmur.javapro.yuka.services.BasketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BasketController.class)
public class BasketControllerTest {

    @MockBean
    private BasketService service;

    @MockBean
    private Mapper mapper;

    @Autowired
    MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getBasketShouldReturnResource() throws Exception {
        Basket basket = new Basket("test@test.fr", new ArrayList<>(Arrays.asList("7622210449283", "737628064502")));

        Mockito.when(service.getBasketAverage(Mockito.any(Basket.class))).thenReturn(
                new BasketAverage(
                        5.5,
                        new NutriScore(3, "Mangeable", 3, 10, "yellow"),
                        new ArrayList<>(Arrays.asList(
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
                                        new NutriScore(3, "Mangeable", 3, 10, "yellow")),
                                new ProductResult.Product("737628064502",
                                        1611,
                                        1.9,
                                        "Rice Noodles",
                                        "Thai peanut noodle kit includes stir-fry rice noodles & thai peanut seasoning",
                                        9.62,
                                        0.72,
                                        1.92,
                                        13.46,
                                        1,
                                        new NutriScore(2, "Bon", 0, 2, "light green"))
                        )))
        );

        Mockito.when(mapper.toDto(Mockito.any(BasketAverage.class))).thenReturn(
                new BasketDTO(5.5,"Mangeable", "yellow", new ArrayList<>(Arrays.asList(
                        new ProductDTO(0,"7622210449283", "BISCUITS FOURRÉS (35%) PARFUM CHOCOLAT - Prince Chocolat", 10, "Mangeable" , "yellow"),
                        new ProductDTO(0,"737628064502", "Rice Noodles - Thai peanut noodle kit includes stir-fry rice noodles & thai peanut seasoning", 1, "Bon" , "light green")
                ))));

        mockMvc.perform(
                    post("/api/basket/")
                    .content(asJsonString(basket))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.average", is(5.5)))
                .andExpect(jsonPath("$.classe", is("Mangeable")))
                .andExpect(jsonPath("$.products[0].nutriScore", is(10)))
                .andExpect(jsonPath("$.products[1].nutriScore", is(1)))
                .andExpect(jsonPath("$.products[1].barCode", is("737628064502")));

    }

    @Test
    public void getBasketShouldReturn422WhenBasketIsEmpty() throws Exception {
        Mockito.when(service.getBasketAverage(Mockito.any(Basket.class))).thenThrow(new BasketEmptyException("The basket must not be empty"));

        mockMvc.perform(
                post("/api/basket/")
                        .content(asJsonString(new Basket("test@test.fr", new ArrayList<>())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }
}
