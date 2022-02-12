package com.delmur.javapro.yuka.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class BasketAverage {

    private double averageNutriScore;
    private NutriScore nutriScoreClass;
    private ArrayList<ProductResult.Product> products;

    public BasketAverage() {
        averageNutriScore = 0;
        nutriScoreClass = new NutriScore();
        products = new ArrayList<ProductResult.Product>();
    }
    public BasketAverage(double averageNutriScore, NutriScore nutriScoreClass, ArrayList<ProductResult.Product> products)
    {
        this.averageNutriScore = averageNutriScore;
        this.nutriScoreClass = nutriScoreClass;
        this.products = products;
    }

}
