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

        NutriScore = CalculateNutriScore(product);
        Classe = "";
        Color = "";

        if(NutriScore <= - 1)
        {
            Classe = "Trop bon";
            Color = "green";
        }
        else if(NutriScore <= 2)
        {
            Classe = "Bon";
            Color = "light green";
        }
        else if(NutriScore <= 10)
        {
            Classe = "Mangeable";
            Color = "yellow";
        }
        else if(NutriScore <= 18)
        {
            Classe = "Mouai";
            Color = "orange";
        }
        else
        {
            Classe = "Degueu";
            Color = "red";
        }

    }

    private int CalculateFatPoints(double fat100g) {
        return Math.min((int)fat100g,10);
    }

    private int CalculateEnergyPoints(double energy100g) {
        return Math.min((int)(energy100g / 335), 10);
    }

    private int CalculateSugarPoints(double sugars100g) {
        /* TODO */
        return Math.min((int)(sugars100g / 4.5), 10);
    }

    private int CalculateSaltPoints(double salt100g) {
        return Math.min((int)(salt100g / 90), 10);
    }

    private int CalculateFiberPoints(double fiber100g){
        /* TODO */
        return Math.min((int)(fiber100g / 0.9),5);
    }

    private int CalculateProteinPoints(double proteins100g){
        return Math.min((int)(proteins100g / 1.6),5);
    }

    private int CalculateNutriScore(JsonNode product) {
        int nutriscore = 0;

        nutriscore += CalculateEnergyPoints(product.findPath("energy_100g").asDouble());
        nutriscore += CalculateFatPoints(product.findPath("saturated-fat_100g").asDouble());
        nutriscore += CalculateSugarPoints(product.findPath("sugars_100g").asDouble());
        nutriscore += CalculateSaltPoints(product.findPath("salt_100g").asDouble());
        nutriscore -= CalculateFiberPoints(product.findPath("fiber_100g").asDouble());
        nutriscore -= CalculateProteinPoints(product.findPath("proteins_100g").asDouble());

        return nutriscore;
    }
}
