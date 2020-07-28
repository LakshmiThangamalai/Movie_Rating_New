package com.movie.ratings.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    @ElementCollection
    private List<String> journals;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Rating> ratings;

    public Movie() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getJournals() {
        return journals;
    }

    public void setJournals(List<String> journals) {
        this.journals = journals;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("journals", journals)
                .append("ratings", ratings)
                .toString();
    }
}
