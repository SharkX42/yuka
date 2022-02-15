package com.delmur.javapro.yuka.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RuleRepositoryTest {

    /* To use that, we need to configure a profile for the test, the data.sql file is loaded for now */
//    @Autowired
//    private TestEntityManager entityManager;

    @Autowired
    private IRuleRepository repository;

    @Test
    public void getEnergyRuleTest() {
        assertEquals(1, repository.getScoreFromNutrientName("energy_100g", 0).getId());
        assertEquals(7, repository.getScoreFromNutrientName("energy_100g", 2011).getId());
        assertEquals(6, repository.getScoreFromNutrientName("energy_100g", 2010).getId());
        assertEquals(11, repository.getScoreFromNutrientName("energy_100g", 7777).getId());
    }

    @Test
    public void getSaturatedFatRuleTest() {
        assertEquals(21, repository.getScoreFromNutrientName("saturated-fat_100g", 0).getId());
        assertEquals(23, repository.getScoreFromNutrientName("saturated-fat_100g", 2.2).getId());
        assertEquals(30, repository.getScoreFromNutrientName("saturated-fat_100g", 10).getId());
        assertEquals(31, repository.getScoreFromNutrientName("saturated-fat_100g", 10.1).getId());
    }

    @Test
    public void getSugarRuleTest() {
        assertEquals(32, repository.getScoreFromNutrientName("sugars_100g", 0).getId());
        assertEquals(36, repository.getScoreFromNutrientName("sugars_100g", 19).getId());
        assertEquals(37, repository.getScoreFromNutrientName("sugars_100g", 27).getId());
        assertEquals(42, repository.getScoreFromNutrientName("sugars_100g", 55).getId());
    }

    @Test
    public void getSaltRuleTest() {
        assertEquals(51, repository.getScoreFromNutrientName("salt_100g", 0).getId());
        assertEquals(59, repository.getScoreFromNutrientName("salt_100g", 753.4).getId());
        assertEquals(59, repository.getScoreFromNutrientName("salt_100g", 810).getId());
        assertEquals(61, repository.getScoreFromNutrientName("salt_100g", 10000).getId());
    }

    @Test
    public void getFiberRuleTest() {
        assertEquals(71, repository.getScoreFromNutrientName("fiber_100g", 0).getId());
        assertEquals(73, repository.getScoreFromNutrientName("fiber_100g", 2.8).getId());
        assertEquals(74, repository.getScoreFromNutrientName("fiber_100g", 2.9).getId());
        assertEquals(76, repository.getScoreFromNutrientName("fiber_100g", 5).getId());
    }

    @Test
    public void getProteinsRuleTest() {
        assertEquals(81, repository.getScoreFromNutrientName("proteins_100g", 0).getId());
        assertEquals(83, repository.getScoreFromNutrientName("proteins_100g", 3.3).getId());
        assertEquals(84, repository.getScoreFromNutrientName("proteins_100g", 6.4).getId());
        assertEquals(86, repository.getScoreFromNutrientName("proteins_100g", 10).getId());
    }

}
