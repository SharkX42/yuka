package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.NutriScore;
import com.delmur.javapro.yuka.models.ProductResult;
import com.delmur.javapro.yuka.repositories.INutriScoreRepository;
import com.delmur.javapro.yuka.repositories.IRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutriScoreService {

    private final IRuleRepository repository;
    private final INutriScoreRepository nutriScoreRepository;
    @Autowired
    public NutriScoreService(IRuleRepository repo, INutriScoreRepository rep) {
        repository = repo;
        nutriScoreRepository = rep;
    }

    public int getNutritionScore(ProductResult.Product product) {
        int score = 0;
        score += repository.getEnergyScore(product.getEnergy_100g());
        score += repository.getSaturatedFlatScore(product.getSaturatedFat_100g());
        score += repository.getSugarScore(product.getSugars_100g());
        score += repository.getSaltScore(product.getSalt_100g());
        score -= repository.getFiberScore(product.getFiber_100g());
        score -= repository.getProteinScore(product.getProteins_100g());
        return score;
    }

    public NutriScore getNutritionScoreClass(int nutritionScore) {
        return nutriScoreRepository.getNutriScoreClass(nutritionScore);
    }

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
