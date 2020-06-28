package com.sparkequation.spring.trial.api.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "BRAND")
public class Brand {
    public Brand() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "BRAND_SEQ")
    @SequenceGenerator(name = "BRAND_SEQ", sequenceName = "BRAND_SEQ", allocationSize = 1, initialValue = 11)
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

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private Integer id;
    @Length(max = 100)
    private String name;
    @Length(max = 100)
    private String country;

    public Brand(String name, String country) {
        this.name = name;
        this.country = country;
    }

}
