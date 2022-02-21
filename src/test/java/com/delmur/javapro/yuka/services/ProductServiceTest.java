package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.models.NutriScore;
import com.delmur.javapro.yuka.models.ProductResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private OpenFoodFactService openFoodFactService;

    @Mock
    private NutriScoreService nutriScoreService;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProductByBarCodeTest()
    {
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
                10,
                new NutriScore(3, "Mangeable", 3, 10, "yellow"));

        ProductResult productResult = new ProductResult(barCode, product);

        Mockito.when(openFoodFactService.getByBarCode(barCode))
                .thenReturn(product);

        Mockito.when(nutriScoreService.getNutritionScore(Mockito.any(ProductResult.Product.class))).thenReturn(10);

        Mockito.when(nutriScoreService.getNutritionScoreClass(Mockito.any(int.class))).thenReturn(new NutriScore(3, "Mangeable", 3, 10, "yellow"));

        assertEquals(product, productService.getProductByBarCode(barCode));
    }
}
