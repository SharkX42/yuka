package com.delmur.javapro.yuka.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

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

    public NutriScore(int id, String classe, int lower_bound, int upper_bound, String color) {
        this.id = id;
        this.classe = classe;
        this.lower_bound = lower_bound;
        this.upper_bound = upper_bound;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NutriScore)) return false;
        NutriScore that = (NutriScore) o;
        return getId() == that.getId() && getLower_bound() == that.getLower_bound() && getUpper_bound() == that.getUpper_bound() && getClasse().equals(that.getClasse()) && getColor().equals(that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClasse(), getLower_bound(), getUpper_bound(), getColor());
    }
}
