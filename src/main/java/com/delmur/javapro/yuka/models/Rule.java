package com.delmur.javapro.yuka.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rule")
@Getter @Setter
public class Rule {
    @Id
    private int id;

    private String name;

    private int points;

    private int min_bound;

    private String component;
}
