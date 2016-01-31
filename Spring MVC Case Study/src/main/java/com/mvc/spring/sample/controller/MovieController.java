package com.mvc.spring.sample.controller;

import com.mvc.spring.sample.model.Movie;
import com.mvc.spring.sample.service.MovieService;
import com.mvc.spring.sample.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Aykut on 31.01.2016.
 */
@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private RateService rateService;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String listMovies(Model model) {
        List<Movie> movieList = movieService.getAll();
        model.addAttribute("movies", movieList);
        return "movie/list-movies";
    }

    @RequestMapping("/movie/new")
    public String newMovie(Model model){
        model.addAttribute("movie", new Movie());
        return "movie/add-movie";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public String saveMovie(Movie movie) {
        movieService.save(movie);
        return "redirect:/movies";
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String getMovie(@PathVariable Integer id, Model model) {
        Movie movie = movieService.getMovie(id);
        model.addAttribute("rates", rateService.getByMovie(movie));
        model.addAttribute("movie", movie);
        return "movie/update-movie";
    }

    @RequestMapping(value = "/movie/edit/{id}", method = RequestMethod.POST)
    public String updateMovie(Movie movie) {
        movieService.save(movie);
        return "redirect:/movies";
    }

    @RequestMapping(value = "/movie/delete/{id}", method = RequestMethod.POST)
    public String deleteMovie(@PathVariable Integer id) {
        movieService.delete(id);
        return "redirect:/movies";
    }

}
