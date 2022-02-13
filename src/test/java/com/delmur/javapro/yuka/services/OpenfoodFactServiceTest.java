package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.ProductResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class OpenfoodFactServiceTest {

    @Autowired
    private OpenFoodFactService service;

    @Test
    void getProductByBarCodeTest() {
        ProductResult.Product productFound = new ProductResult.Product("7622210449283",
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

        assertEquals(productFound, service.getByBarCode("7622210449283"));
    }

    @Test
    void getNotFoundProductByBarCodeTest() {
        assertNull(service.getByBarCode("7622210449282"));
    }

}
