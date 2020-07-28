package com.movie.ratings.web;

import com.movie.ratings.model.Customer;
import com.movie.ratings.model.CustomerRating;
import com.movie.ratings.model.Movie;
import com.movie.ratings.model.Rating;
import com.movie.ratings.service.CustomerService;
import com.movie.ratings.service.MovieRatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MovieRatingsController {

    private final CustomerService customerService;
    private final MovieRatingService movieRatingService;

    public MovieRatingsController(CustomerService customerService,
                                  MovieRatingService movieRatingService) {
        this.customerService = customerService;
        this.movieRatingService = movieRatingService;
    }

    @PostMapping("/customer")
    public ResponseEntity<Integer> addCustomer(@RequestBody Customer customer) {
        final Integer customerId = this.customerService.addCustomer(customer).getId();
        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer customerId) {
        final Optional<Customer> customer = this.customerService.getCustomer(customerId);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/movie")
    public ResponseEntity<Integer> addMovie(@RequestBody Movie movie) {
        final Integer movieId = this.movieRatingService.addMovie(movie);
        return new ResponseEntity<>(movieId, HttpStatus.CREATED);
    }

    @PostMapping("/movie/{movieId}/rating")
    public ResponseEntity<Integer> addRating(@PathVariable Integer movieId, @RequestBody Rating rating) {
        final Integer ratingId = this.movieRatingService.addRating(movieId, rating);
        return new ResponseEntity<>(ratingId, HttpStatus.CREATED);
    }

    @GetMapping("/movie/highrated")
    public Movie getHighRatedMovie(){
        return this.movieRatingService.getHighRated();
    }

    @GetMapping("/customer/highrated")
    public CustomerRating getHighAverageCustomer(){
        return this.movieRatingService.getHighCustomerRating();
    }


}
