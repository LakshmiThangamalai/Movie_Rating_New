package com.movie.ratings.repo;

import com.movie.ratings.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
