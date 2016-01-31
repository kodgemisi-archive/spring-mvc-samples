package com.mvc.spring.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.spring.sample.model.Movie;
import com.mvc.spring.sample.repository.MovieRepository;

@Service
@Transactional
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Movie create(Movie movie) {
		return movieRepository.save(movie);
	}
	
	public Iterable<Movie> findAll() {
		return movieRepository.findAll();
	}

}
