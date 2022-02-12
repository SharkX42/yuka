package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.Basket;
import com.delmur.javapro.yuka.models.BasketAverage;
import com.delmur.javapro.yuka.models.ProductResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    private final ProductService productService;
    private final NutriScoreService nutriScoreService;

    @Autowired
    public BasketService(ProductService productService, NutriScoreService nutriScoreService)
    {
        this.productService = productService;
        this.nutriScoreService = nutriScoreService;
    }

    public BasketAverage getBasketAverage(Basket basket) throws Exception {
        BasketAverage basketAverage = new BasketAverage();
        ProductResult.Product product;
        double average = 0;
        int size = basket.getProducts().size();

        for(String barCode : basket.getProducts()) {
            product = productService.getProductByBarCode(barCode);
            average += product.getNutriScore();
            basketAverage.getProducts().add(product);
        }

        if (size == 0)
            size = 1; //TODO : throw Exception

        basketAverage.setAverageNutriScore(average / size);
        basketAverage.setNutriScoreClass(nutriScoreService.getNutritionScoreClass((int)average/size));
        return basketAverage;
    }


}
