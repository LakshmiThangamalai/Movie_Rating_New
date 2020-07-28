package com.movie.ratings.service;

import com.movie.ratings.model.Customer;
import com.movie.ratings.model.CustomerRating;
import com.movie.ratings.model.Movie;
import com.movie.ratings.model.Rating;
import com.movie.ratings.repo.MovieRepository;
import com.movie.ratings.repo.RatingRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class MovieRatingServiceImpl implements MovieRatingService {

    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    public MovieRatingServiceImpl(MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    @Transactional
    public Integer addMovie(Movie movie) {
        return this.movieRepository.save(movie).getId();
    }

    @Override
    @Transactional
    public Integer addRating(Integer movieId, Rating rating) {
        final Movie movie = this.movieRepository.getOne(movieId);
        rating.setMovie(movie);
        movie.getRatings().add(rating);
        this.movieRepository.save(movie);
        return rating.getId();
    }

    @Override
    @Transactional
    public Movie getHighRated() {
        return this.movieRepository.findAll().stream()
                .filter(movie -> !CollectionUtils.isEmpty(movie.getRatings()))
                .map(movie -> {
                    Double average = movie.getRatings().stream().mapToInt(Rating::getValue).average().orElse(0);
                    return Pair.of(movie, average);
                })
                .max(Comparator.comparing(Pair::getSecond)).get().getFirst();
    }

    @Override
    @Transactional
    public CustomerRating getHighCustomerRating() {
        final Map<Customer, Double> customerRatings = this.ratingRepository.findAll().stream()
                .collect(Collectors.groupingBy(Rating::getCustomer, Collectors.averagingDouble(Rating::getValue)));

        final Entry<Customer, Double> highestAverageCustomer = customerRatings.entrySet()
                .stream().max(Comparator.comparingDouble(Entry::getValue)).get();
        final OptionalDouble allCustomerAverage = customerRatings.entrySet().stream()
                .mapToDouble(Map.Entry::getValue).average();

        final Customer customer = highestAverageCustomer.getKey();
        CustomerRating customerRating = new CustomerRating();
        customerRating.setCustomerId(customer.getId());
        customerRating.setFirstName(customer.getFirstName());
        customerRating.setLastName(customer.getLastName());
        customerRating.setCustomerAverageRating(highestAverageCustomer.getValue());
        customerRating.setAverageRating(allCustomerAverage.getAsDouble());
        return customerRating;
    }


}
