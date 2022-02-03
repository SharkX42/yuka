package com.delmur.javapro.yuka.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ProductResult implements Serializable {

    private String Code;
    private Product product;

    @Getter @Setter
    public static class Product implements Serializable {
        private String Code;
        private double Energy_100g;
        private double Fiber_100g;
        private String Generic_name;
        private String Product_name;
        private double Proteins_100g;
        private double Salt_100g;
        @JsonAlias("saturated-fat_100g")
        private double SaturatedFat_100g;
        private double Sugars_100g;
        private int NutriScore;
        private NutriScore NutriScoreClass;

        public Product() {

        }

    }

    public ProductResult() {

    }
}
