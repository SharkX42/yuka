package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.NutriScore;
import com.delmur.javapro.yuka.models.ProductResult;
import com.delmur.javapro.yuka.models.Rule;
import com.delmur.javapro.yuka.repositories.INutriScoreRepository;
import com.delmur.javapro.yuka.repositories.IRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NutriScoreService {

    private final IRuleRepository repository;
    private final INutriScoreRepository nutriScoreRepository;
    private final List<String> nutrientsList;

    @Autowired
    public NutriScoreService(IRuleRepository repo, INutriScoreRepository rep) {
        repository = repo;
        nutriScoreRepository = rep;
        /* List of all nutrients that are taken into account for nutriscore calculation */
        nutrientsList = new ArrayList<>(Arrays.asList(
                "energy_100g",
                "saturated-fat_100g",
                "sugars_100g",
                "salt_100g",
                "fiber_100g",
                "proteins_100g"
        ));
    }

    public int getNutritionScore(ProductResult.Product product) {
        int score = 0;
        Rule rule;
        /* List the values of all nutrients that are taken into account for nutriscore calculation */
        ArrayList<Double> nutrientsValues = new ArrayList<>(Arrays.asList(
                product.getEnergy_100g(),
                product.getSaturatedFat_100g(),
                product.getSugars_100g(),
                product.getSalt_100g(),
                product.getFiber_100g(),
                product.getProteins_100g()
        ));

        /* For each nutrient, we calculate its points */
        for (int i=0; i<nutrientsList.size(); i++) {
            rule = repository.getScoreFromNutrientName(nutrientsList.get(i), nutrientsValues.get((i)));
            if(rule.getComponent().equals("N")) {
                score += rule.getPoints();
            }
            else {
                score -= rule.getPoints();
            }
        }

        return score;
    }

    public NutriScore getNutritionScoreClass(int nutritionScore) {
        return nutriScoreRepository.getNutriScoreClass(nutritionScore);
    }

    /* Unused at the moment */
    public double getAverageNutriScore(List<ProductResult.Product> basket)
    {
        if(basket.size() != 0)
        {
            double average = 0.0;
            for(ProductResult.Product product : basket)
            {
                average += product.getNutriScore();
            }
            return average/ basket.size();
        }
        return 0; //TODO : exception
    }

}
