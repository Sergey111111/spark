package com.sparkequation.spring.trial.api.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class Category {
    public Category() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CATEGORY_SEQ")
    @SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "CATEGORY_SEQ", allocationSize = 1, initialValue = 7)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer id;

    @Length(max = 100)
    private String name;

    public Category(String name) {
        this.name = name;
    }

}
