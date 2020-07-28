package com.movie.ratings.service;

import com.movie.ratings.model.CustomerRating;
import com.movie.ratings.model.Movie;
import com.movie.ratings.model.Rating;

public interface MovieRatingService {

    Integer addMovie(Movie movie);
    Integer addRating(Integer movieId, Rating rating);
    Movie getHighRated();
    CustomerRating getHighCustomerRating();

}
