package com.mvc.spring.sample.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.spring.sample.model.Comment;
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

	public Movie findById(Integer id) {
		return movieRepository.findOne(id);
	}

	public Movie update(Movie movie) {
		return movieRepository.save(movie);
	}

	public void addComment(Movie movie, Comment comment) {
		comment.setDate(Calendar.getInstance());
		movie.addComment(comment);
		movieRepository.save(movie);
	}
	
	//TODO implement delete

}
