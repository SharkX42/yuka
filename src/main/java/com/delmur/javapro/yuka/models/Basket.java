package com.delmur.javapro.yuka.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

/* The class we will be receiving when a client wants to know information about it's basket */

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
