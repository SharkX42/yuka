package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.NutriScore;
import com.delmur.javapro.yuka.models.ProductResult;
import com.delmur.javapro.yuka.repositories.INutriScoreRepository;
import com.delmur.javapro.yuka.repositories.IRuleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class NutriScoreServiceTest {
    @MockBean
    private IRuleRepository ruleRepository;

    @MockBean
    private INutriScoreRepository nutriScoreRepository;

    @Autowired
    private NutriScoreService service;

    @Test
    void getNutriScoreTest() {
        ProductResult.Product product = new ProductResult.Product("7622210449283",
                1962,
                4,
                "BISCUITS FOURRÉS (35%) PARFUM CHOCOLAT",
                "Prince Chocolat",
                6.3,
                0.49,
                5.6,
                32,
                10,
                new NutriScore(3, "Mangeable", 3, 10, "yellow"));

        Mockito.when(ruleRepository.getEnergyScore(Mockito.any(double.class))).thenReturn(5);
        Mockito.when(ruleRepository.getSaturatedFlatScore(Mockito.any(double.class))).thenReturn(5);
        Mockito.when(ruleRepository.getSugarScore(Mockito.any(double.class))).thenReturn(7);
        Mockito.when(ruleRepository.getSaltScore(Mockito.any(double.class))).thenReturn(0);
        Mockito.when(ruleRepository.getFiberScore(Mockito.any(double.class))).thenReturn(4);
        Mockito.when(ruleRepository.getProteinScore(Mockito.any(double.class))).thenReturn(3);

        assert service.getNutritionScore(product) == 10;
    }
}
