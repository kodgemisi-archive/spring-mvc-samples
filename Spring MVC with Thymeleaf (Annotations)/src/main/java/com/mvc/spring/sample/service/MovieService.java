package com.mvc.spring.sample.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mvc.spring.sample.model.Comment;
import com.mvc.spring.sample.model.Movie;
import com.mvc.spring.sample.repository.MovieRepository;

@Service
@Transactional
public class MovieService {
	
	private static final String DEFAULT_IMG_URL = "https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg";
	private static final String POSTER_API = "http://www.omdbapi.com/?i=";
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Movie create(Movie movie) {
		String posterUrl = this.getPosterUrl(movie.getImdbId());
		movie.setPosterUrl(posterUrl);
		
		return movieRepository.save(movie);
	}
	
	public Iterable<Movie> findAll() {
		return movieRepository.findAll();
	}

	public Movie findById(Integer id) {
		return movieRepository.findOne(id);
	}

	public Movie update(Movie movie) {
		String posterUrl = this.getPosterUrl(movie.getImdbId());
		movie.setPosterUrl(posterUrl);
		
		return movieRepository.save(movie);
	}

	public void addComment(Movie movie, Comment comment) {
		comment.setDate(Calendar.getInstance());
		movie.addComment(comment);
		movieRepository.save(movie);
	}
	
	public String getPosterUrl(String imdbId) {
		String result;
		try {
			HttpResponse<JsonNode> response = Unirest.get(POSTER_API + imdbId).asJson();
			result = (String) response.getBody().getObject().get("Poster");
		} catch (UnirestException e) {
			e.printStackTrace();
			result = DEFAULT_IMG_URL;
		}
		return result;
	}
	
	//TODO implement delete

}
