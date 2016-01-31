package com.mvc.spring.sample.controller;

import com.mvc.spring.sample.model.Rate;
import com.mvc.spring.sample.service.MovieService;
import com.mvc.spring.sample.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RateController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private RateService rateService;

    @RequestMapping("/rate/new")
    public String newRate(Model model){
        model.addAttribute("movies", movieService.getAll());
        model.addAttribute("rate", new Rate());
        return "rate/add-rate";
    }

    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public String saveMovie(Rate rate) {
        rateService.save(rate);
        return "redirect:/rate/new";
    }
}
