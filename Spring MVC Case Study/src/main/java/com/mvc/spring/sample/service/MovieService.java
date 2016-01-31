package com.mvc.spring.sample.service;

import com.google.common.collect.Lists;
import com.mvc.spring.sample.model.Movie;
import com.mvc.spring.sample.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Aykut on 31.01.2016.
 */
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAll() {
        return Lists.newArrayList(movieRepository.findAll());
    }

    public Movie getMovie(Integer id) {
        return movieRepository.findOne(id);
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public void delete(Integer id) {
        movieRepository.delete(id);
    }

}
