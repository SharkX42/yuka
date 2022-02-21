package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.NutriScore;
import com.delmur.javapro.yuka.models.ProductResult;
import com.delmur.javapro.yuka.models.Rule;
import com.delmur.javapro.yuka.repositories.INutriScoreRepository;
import com.delmur.javapro.yuka.repositories.IRuleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class NutriScoreServiceTest {

    @Mock
    private IRuleRepository ruleRepository;

    @Mock
    private INutriScoreRepository nutriScoreRepository;

    @InjectMocks
    private NutriScoreService service;

    private ArrayList<ProductResult.Product> list;

    @BeforeEach
    void setup() {
        list = new ArrayList<>();

        list.add(new ProductResult.Product("7622210449283",
                1962,
                4,
                "BISCUITS FOURRÉS (35%) PARFUM CHOCOLAT",
                "Prince Chocolat",
                6.3,
                0.49,
                5.6,
                32,
                10,
                new NutriScore(3, "Mangeable", 3, 10, "yellow")));

        list.add(new ProductResult.Product("737628064502",
                1611,
                1.9,
                "Rice Noodles",
                "Thai peanut noodle kit includes stir-fry rice noodles & thai peanut seasoning",
                9.62,
                0.72,
                1.92,
                13.46,
                1,
                new NutriScore(2, "Bon", 0, 2, "light green")));
    }

    @Test
    public void getNutriScoreTest() {

        Mockito.when(ruleRepository.getScoreFromNutrientName("energy_100g" , 1962)).thenReturn(new Rule( 5,  "N"));
        Mockito.when(ruleRepository.getScoreFromNutrientName("saturated-fat_100g" , 5.6)).thenReturn(new Rule( 5,  "N"));
        Mockito.when(ruleRepository.getScoreFromNutrientName("sugars_100g" , 32)).thenReturn(new Rule( 7,  "N"));
        Mockito.when(ruleRepository.getScoreFromNutrientName("salt_100g" , 0.49)).thenReturn(new Rule( 0,  "N"));
        Mockito.when(ruleRepository.getScoreFromNutrientName("fiber_100g" , 4)).thenReturn(new Rule( 4,  "P"));
        Mockito.when(ruleRepository.getScoreFromNutrientName("proteins_100g" , 6.3)).thenReturn(new Rule( 3,  "P"));

        assertEquals(10, service.getNutritionScore(list.get(0)));
    }

    @Test
    public void getAverageNutriScore() {
        assertEquals(5.5, service.getAverageNutriScore(list));
        assertEquals(0, service.getAverageNutriScore(new ArrayList<>()));
    }
}
