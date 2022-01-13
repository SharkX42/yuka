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
    private double CarboHydrates100g;
    private double Energy100g;
    private double Sugars100g;
    private double Salt100g;

    public Product() {

    }
    public Product(JsonNode product)
    {
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

        CarboHydrates100g = nutriments.path("carbohydrates_100g").asDouble();
        Energy100g = nutriments.path("energy_100g").asDouble();
        Sugars100g = nutriments.path("sugars_100g").asDouble();
        Salt100g = nutriments.path("salt_100g").asDouble();

    }
}
