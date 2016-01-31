package com.mvc.spring.sample.repository;

import com.mvc.spring.sample.model.Movie;
import com.mvc.spring.sample.model.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RateRepository extends CrudRepository<Rate, Integer> {
    Set<Rate> findByMovie(Movie movie);
}
