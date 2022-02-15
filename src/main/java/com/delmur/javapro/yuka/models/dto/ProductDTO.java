package com.delmur.javapro.yuka.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private long Id;
    private String BarCode;
    private String Name;
    private int NutriScore;
    private String Classe;
    private String Color;

    public ProductDTO(long id, String barCode, String name, int nutriScore, String classe, String color) {
        Id = id;
        BarCode = barCode;
        Name = name;
        NutriScore = nutriScore;
        Classe = classe;
        Color = color;
    }
}
