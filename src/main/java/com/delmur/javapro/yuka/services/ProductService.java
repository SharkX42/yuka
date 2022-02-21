package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.NutriScore;
import com.delmur.javapro.yuka.models.ProductResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final OpenFoodFactService openFoodFactService;
    private final NutriScoreService nutriScoreService;

    @Autowired
    public ProductService(OpenFoodFactService oservice, NutriScoreService nservice) {
        openFoodFactService = oservice;
        nutriScoreService = nservice;
    }

    public ProductResult.Product getProductByBarCode(String barCode) {
        /* Get the product via the API */
        ProductResult.Product product = openFoodFactService.getByBarCode(barCode);

        /* If the product exists, we calculate its nutriscore */
        if (product != null) {
            product.setNutriScore(nutriScoreService.getNutritionScore(product));

            product.setNutriScoreClass(nutriScoreService.getNutritionScoreClass(product.getNutriScore()));
        }

        return product;
    }

}
