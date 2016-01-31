package com.mvc.spring.sample.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aykut on 31.01.2016.
 */
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @OneToMany(mappedBy = "movie")
    private Set<Rate> rates = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }
}
