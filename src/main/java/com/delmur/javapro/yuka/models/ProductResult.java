package com.delmur.javapro.yuka.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
public class ProductResult implements Serializable {

    private String Code;
    private Product product;

    public ProductResult(String code, Product product) {
        Code = code;
        this.product = product;
    }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Product)) return false;
            Product product = (Product) o;
            return Double.compare(product.getEnergy_100g(), getEnergy_100g()) == 0 && Double.compare(product.getFiber_100g(), getFiber_100g()) == 0 && Double.compare(product.getProteins_100g(), getProteins_100g()) == 0 && Double.compare(product.getSalt_100g(), getSalt_100g()) == 0 && Double.compare(product.getSaturatedFat_100g(), getSaturatedFat_100g()) == 0 && Double.compare(product.getSugars_100g(), getSugars_100g()) == 0 && getNutriScore() == product.getNutriScore() && getCode().equals(product.getCode()) && getGeneric_name().equals(product.getGeneric_name()) && getProduct_name().equals(product.getProduct_name()) && Objects.equals(getNutriScoreClass(), product.getNutriScoreClass());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getCode(), getEnergy_100g(), getFiber_100g(), getGeneric_name(), getProduct_name(), getProteins_100g(), getSalt_100g(), getSaturatedFat_100g(), getSugars_100g(), getNutriScore(), getNutriScoreClass());
        }

        public Product(String code,
                       double energy_100g,
                       double fiber_100g,
                       String generic_name,
                       String product_name,
                       double proteins_100g,
                       double salt_100g,
                       double saturatedFat_100g,
                       double sugars_100g,
                       int nutriScore,
                       NutriScore nutriScoreClass) {
            Code = code;
            Energy_100g = energy_100g;
            Fiber_100g = fiber_100g;
            Generic_name = generic_name;
            Product_name = product_name;
            Proteins_100g = proteins_100g;
            Salt_100g = salt_100g;
            SaturatedFat_100g = saturatedFat_100g;
            Sugars_100g = sugars_100g;
            NutriScore = nutriScore;
            NutriScoreClass = nutriScoreClass;
        }
    }

    public ProductResult() {

    }
}
