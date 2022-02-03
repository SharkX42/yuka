package com.delmur.javapro.yuka.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nutrition_score")
@Getter @Setter
public class NutriScore {
    @Id
    private int id;
    private String classe;
    private int lower_bound;
    private int upper_bound;
    private String color;

    public NutriScore() {

    }
}
