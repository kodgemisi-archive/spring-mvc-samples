package com.mvc.spring.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvc.spring.sample.model.Movie;
import com.mvc.spring.sample.service.MovieService;

@Controller
@RequestMapping(path = "/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public String newForm() {
		
		return "movies/movieForm";
	}
	
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public String create(@ModelAttribute Movie movie, Model model ) {
		
		System.out.println(movie);
		
		movieService.create(movie);
		
		model.addAttribute("message", "Movie created"); //FIXME
		return "redirect:/movies";//TODO why?
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public String list(Model model ) {
		
		Iterable<Movie> movies = movieService.findAll();

		model.addAttribute("movieList", movies);
		return "movies/movieList";
	}
	

}
