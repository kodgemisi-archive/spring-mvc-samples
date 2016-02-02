package com.mvc.spring.sample.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.spring.sample.controller.validator.DirectorValidator;
import com.mvc.spring.sample.model.Comment;
import com.mvc.spring.sample.model.Movie;
import com.mvc.spring.sample.service.MovieService;

@Controller
@RequestMapping(path = "/movies")
public class MovieController {

	private static final String LAST_MOVIES_SESSION_KEY = "last_movies";

	// TODO implement delete

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private DirectorValidator directorValidator;
	
    @InitBinder("movie")
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(this.directorValidator);
    }

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public String newForm(Model model) {

		model.addAttribute("movie", new Movie());
		return "movies/movieForm";
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public String create(
			Model model,
			@ModelAttribute @Valid Movie movie,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("movie", movie);
			return "movies/movieForm"; 
		}

		System.out.println(movie);

		movieService.create(movie);

		redirectAttributes.addFlashAttribute("message", "Movie created");
		return "redirect:/movies";
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String list(Model model, HttpSession session) {

		Iterable<Movie> movies = movieService.findAll();
		
		Object countObj = session.getAttribute("listViewCount");
		Integer count;
		if(countObj == null) {
			count = 0;
		}
		else{
			count = (Integer) countObj;
		}
		count++;
		session.setAttribute("listViewCount", count);	
		
		
		List<Movie> lastShownMovies = (List<Movie>) session.getAttribute(LAST_MOVIES_SESSION_KEY);
		
		model.addAttribute("lastShownMovies", lastShownMovies);
		model.addAttribute("pageViewCount", count);
		model.addAttribute("movieList", movies);
		return "movies/movieList";
	}

	@RequestMapping(path = "/{id}/update", method = RequestMethod.GET)
	public String updateForm(Model model, @PathVariable("id") Integer id) {

		Movie movie = movieService.findById(id);

		model.addAttribute("movie", movie);
		return "movies/movieUpdateForm";
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute Movie movie, @PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {

		movieService.update(movie);

		redirectAttributes.addFlashAttribute("message", "Movie updated");
		return "redirect:/movies";
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public String details(@PathVariable("id") Integer id, Model model, 
			HttpSession session) {

		Movie movie = movieService.findById(id);
		
		List<Movie> movies = (List<Movie>) session.getAttribute(LAST_MOVIES_SESSION_KEY);
		
		if(movies == null) {
			movies = new ArrayList<>();
		}
		
		movies.add(movie);
		
		session.setAttribute(LAST_MOVIES_SESSION_KEY, movies);
		
		model.addAttribute("movie", movie);
		model.addAttribute("comment", new Comment());
		return "movies/movieDetails";
	}

	@RequestMapping(path = "/{id}/comments", method = RequestMethod.POST)
	public String addComment(
			@ModelAttribute Comment comment,
			@PathVariable("id") Integer id, 
			RedirectAttributes redirectAttributes) {
		
		//FIXME
		comment.setId(null);

		Movie movie = movieService.findById(id);
		movieService.addComment(movie, comment);

		redirectAttributes.addFlashAttribute("message", "Comment added");
		return "redirect:/movies/" + id;
	}

}
