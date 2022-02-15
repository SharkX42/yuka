package com.delmur.javapro.yuka.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter @Setter
public class Basket implements Serializable {

    private String email;
    private ArrayList<String> products;

    public Basket(){

    }

    public Basket(String email, ArrayList<String> products)
    {
        this.email = email;
        this.products = products;
    }
}
