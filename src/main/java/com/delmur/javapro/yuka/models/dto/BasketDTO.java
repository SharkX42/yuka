package com.delmur.javapro.yuka.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class BasketDTO {

    private double average;
    private String classe;
    private String color;
    private ArrayList<ProductDTO> products;

    public BasketDTO(double average, String classe, String color, ArrayList<ProductDTO> products) {
        this.average = average;
        this.classe = classe;
        this.color = color;
        this.products = products;
    }
}
