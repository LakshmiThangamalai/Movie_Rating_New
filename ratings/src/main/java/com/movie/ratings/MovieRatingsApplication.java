package com.movie.ratings;

import com.movie.ratings.model.Customer;
import com.movie.ratings.model.CustomerRating;
import com.movie.ratings.model.Movie;
import com.movie.ratings.model.Rating;
import com.movie.ratings.repo.MovieRepository;
import com.movie.ratings.service.CustomerService;
import com.movie.ratings.service.MovieRatingService;
import com.movie.ratings.service.MovieRatingServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MovieRatingsApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(MovieRatingsApplication.class, args);

		final MovieRatingService ratingService = context.getBean(MovieRatingService.class);
		Customer customer = new Customer();
		customer.setFirstName("Lakshmi");

		Movie movie = new Movie();
		movie.setName("WonderWoman");
		movie.setJournals(Arrays.asList("Fantasy"));

		final CustomerService customerService = context.getBean(CustomerService.class);
		final Customer customer2 = customerService.addCustomer(customer);
		Rating rating = new Rating();
		rating.setCustomer(customer2);
		rating.setValue(5);
		final int id = ratingService.addMovie(movie);
		movie.setId(id);
		Rating rating2 = new Rating();
		rating2.setValue(2);
		rating2.setCustomer(customer2);

		ratingService.addRating(id, rating);
		ratingService.addRating(id, rating2);

		final Movie highRated = ratingService.getHighRated();
		System.out.println("HighRated Movie---"+movie);

		final CustomerRating highCustomerRating = ratingService.getHighCustomerRating();
		System.out.println("Customer HighRated-"+highCustomerRating);

	}

}
