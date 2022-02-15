package com.delmur.javapro.yuka.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "rule")
@Getter @Setter
public class Rule {
    @Id
    private int id;

    private String name;

    private int points;

    private double min_bound;

    private String component;

    public Rule(int points, String component) {
        this.points = points;
        this.component = component;
    }

    public Rule(int id, String name, int points, double min_bound, String component) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.min_bound = min_bound;
        this.component = component;
    }

    public Rule() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rule)) return false;
        Rule rule = (Rule) o;
        return getId() == rule.getId() && getPoints() == rule.getPoints() && Double.compare(rule.getMin_bound(), getMin_bound()) == 0 && getName().equals(rule.getName()) && getComponent().equals(rule.getComponent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPoints(), getMin_bound(), getComponent());
    }
}
