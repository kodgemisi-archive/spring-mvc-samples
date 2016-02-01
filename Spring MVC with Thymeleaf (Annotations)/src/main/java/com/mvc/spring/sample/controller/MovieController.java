package com.mvc.spring.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.spring.sample.model.Comment;
import com.mvc.spring.sample.model.Movie;
import com.mvc.spring.sample.service.MovieService;

@Controller
@RequestMapping(path = "/movies")
public class MovieController {

	// TODO implement delete

	@Autowired
	private MovieService movieService;

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public String newForm(Model model) {

		model.addAttribute("movie", new Movie());
		return "movies/movieForm";
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public String create(@ModelAttribute Movie movie, Model model, RedirectAttributes redirectAttributes) {

		System.out.println(movie);

		movieService.create(movie);

		redirectAttributes.addFlashAttribute("message", "Movie created");
		return "redirect:/movies";
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String list(Model model) {

		Iterable<Movie> movies = movieService.findAll();

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
	public String details(@PathVariable("id") Integer id, Model model) {

		Movie movie = movieService.findById(id);

		model.addAttribute("movie", movie);
		model.addAttribute("comment", new Comment());
		return "movies/movieDetails";
	}

	@RequestMapping(path = "/{id}/comments", method = RequestMethod.POST)
	public String addComment(@PathVariable("id") Integer id, @ModelAttribute Comment comment,
			RedirectAttributes redirectAttributes) {

		Movie movie = movieService.findById(id);
		movieService.addComment(movie, comment);

		redirectAttributes.addFlashAttribute("message", "Comment added");
		return "redirect:/movies/" + id;
	}

}
