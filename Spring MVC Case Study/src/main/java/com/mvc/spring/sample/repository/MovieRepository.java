package com.mvc.spring.sample.repository;

import com.mvc.spring.sample.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Aykut on 31.01.2016.
 */
@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
}
