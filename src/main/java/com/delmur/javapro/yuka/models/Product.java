package com.delmur.javapro.yuka.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@Getter @Setter
public class Product {

    private int Id;
    private String BarCode;
    private String Name;
    private int NutriScore;
    private String Classe;
    private String Color;
//    private double CarboHydrates100g;
//    private double Energy100g;
//    private double Sugars100g;
//    private double Salt100g;

    public Product() {

    }
    public Product(JsonNode product)
    {
        double SaturatedFat100g;
        double Energy100g;
        double Sugars100g;
        double Salt100g;

        Id = product.path("code").asInt();
        BarCode = product.path("code").asText();

        Name = product.findPath("generic_name").asText().toLowerCase();

        Name = String.join(" - ",
                Name,
                product.findPath("product_name").asText().toLowerCase());

        NutriScore = 0;
        Classe = "";
        Color = "";

        JsonNode nutriments = product.findPath("nutriments");

        SaturatedFat100g = nutriments.path("saturated-fat_100g").asDouble();
        Energy100g = nutriments.path("energy_100g").asDouble();
        Sugars100g = nutriments.path("sugars_100g").asDouble();
        Salt100g = nutriments.path("salt_100g").asDouble();

        /* TODO : Rework for taking db value into account */
        NutriScore += Math.min((int)(Energy100g / 335), 10);
        NutriScore += Math.min((int)SaturatedFat100g,10);
        NutriScore += Math.min((int)(Sugars100g / 4.5), 10);
        NutriScore += Math.min((int)(Salt100g / 90), 10);
        NutriScore -= Math.min((int)(nutriments.path("fiber_100g").asDouble()  / 0.9),5);
        NutriScore -= Math.min((int)(nutriments.path("proteins_100g").asDouble()  / 1.6),5);
    }
}
