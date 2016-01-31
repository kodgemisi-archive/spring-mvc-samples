package com.mvc.spring.sample.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.spring.sample.model.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

}
