package com.movie.ratings.repo;

import com.movie.ratings.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Integer> {

    List<Rating> findAllByCustomer_Id(Integer customerId);

}
