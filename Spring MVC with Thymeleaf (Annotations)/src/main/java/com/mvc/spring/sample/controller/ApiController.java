package com.mvc.spring.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.spring.sample.model.Movie;
import com.mvc.spring.sample.service.MovieService;

@Controller
@RequestMapping(path = "/api")
public class ApiController {

	@Autowired
	private MovieService movieService;

	@RequestMapping(path = "/movies", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Movie> addComment() {
		
		Iterable<Movie> movies = movieService.findAll();

		return movies;
	}

}
