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
}
