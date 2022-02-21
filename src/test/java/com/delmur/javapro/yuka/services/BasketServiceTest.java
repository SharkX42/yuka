package com.delmur.javapro.yuka.services;

import com.delmur.javapro.yuka.exceptions.BasketEmptyException;
import com.delmur.javapro.yuka.models.Basket;
import com.delmur.javapro.yuka.models.BasketAverage;
import com.delmur.javapro.yuka.models.NutriScore;
import com.delmur.javapro.yuka.models.ProductResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private NutriScoreService nutriScoreService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void getBasketAverage() throws Exception {
        ArrayList<String> listProducts = new ArrayList<String>();
        listProducts.add("7622210449283");
        listProducts.add("7622210449283");
        listProducts.add("737628064502");

        Basket basket = new Basket("test@test.fr", listProducts);

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

        ProductResult.Product product2 = new ProductResult.Product("737628064502",
                1611,
                1.9,
                "Rice Noodles",
                "Thai peanut noodle kit includes stir-fry rice noodles & thai peanut seasoning",
                0,
                0.72,
                1.92,
                13.46,
                1,
                new NutriScore(2, "Bon", 0, 2, "light green"));

        Mockito.when(productService.getProductByBarCode("7622210449283")).thenReturn(product);
        Mockito.when(productService.getProductByBarCode("737628064502")).thenReturn(product2);

        Mockito.when(nutriScoreService.getNutritionScoreClass(7)).thenReturn(new NutriScore(3, "Mangeable", 3, 10, "yellow"));

        ArrayList<ProductResult.Product> productResults = new ArrayList<>();
        productResults.add(product);
        productResults.add(product);
        productResults.add(product2);

        BasketAverage basketAverage = new BasketAverage(7 , new NutriScore(3, "Mangeable", 3, 10, "yellow"), productResults);

        assertTrue(basketAverage.equals(basketService.getBasketAverage(basket)));
    }

    @Test
    void getBasketAverageEmpty()
    {
        ArrayList<String> listProducts = new ArrayList<String>();

        Basket basket = new Basket("test@test.fr", listProducts);

        Exception exception = assertThrows(BasketEmptyException.class, () -> {
            basketService.getBasketAverage(basket);
        });

        String expectedMessage = "The basket must not be empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
