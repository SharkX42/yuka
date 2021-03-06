package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.exceptions.BasketEmptyException;
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

        if (size == 0)
            throw new BasketEmptyException("The basket must not be empty");

        /* for each product in the basket, we get the product's information and we update the average */
        for(String barCode : basket.getProducts()) {
            product = productService.getProductByBarCode(barCode);
            average += product.getNutriScore();
            basketAverage.getProducts().add(product);
        }

        basketAverage.setAverageNutriScore(average / size);
        basketAverage.setNutriScoreClass(nutriScoreService.getNutritionScoreClass((int)average/size));
        return basketAverage;
    }
}
