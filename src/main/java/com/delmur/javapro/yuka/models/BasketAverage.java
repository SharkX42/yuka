package com.delmur.javapro.yuka.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketAverage)) return false;
        BasketAverage that = (BasketAverage) o;
        return Double.compare(that.getAverageNutriScore(), getAverageNutriScore()) == 0 && getNutriScoreClass().equals(that.getNutriScoreClass()) && getProducts().equals(that.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAverageNutriScore(), getNutriScoreClass(), getProducts());
    }
}
