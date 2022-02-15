package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.ProductResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)

public class OpenfoodFactServiceTest {

    private String url = "%s.json?";

    @Mock
    private RestTemplate restTemplate;

    private OpenFoodFactService service;

    @BeforeEach
    void setup() {
        service = new OpenFoodFactService(restTemplate, url);
    }

    @Test
    void getProductByBarCodeTest() {
        String barCode = "7622210449283";
        ProductResult.Product product = new ProductResult.Product(barCode,
        1962,
        4,
        "BISCUITS FOURRÉS (35%) PARFUM CHOCOLAT",
        "Prince Chocolat",
        6.3,
        0.49,
        5.6,
        32,
        0,
        null);

        ProductResult productResult = new ProductResult(barCode, product);

        Mockito.when(restTemplate.getForObject(String.format(url, barCode), ProductResult.class))
                .thenReturn(productResult);

        assertEquals(product, service.getByBarCode(barCode));
    }

    @Test
    void getNotFoundProductByBarCodeTest() {
        String barCode = "7622210449282";

        Mockito.when(restTemplate.getForObject(String.format(url, barCode), ProductResult.class))
                .thenReturn(null);
        assertNull(service.getByBarCode(barCode));
    }

}
