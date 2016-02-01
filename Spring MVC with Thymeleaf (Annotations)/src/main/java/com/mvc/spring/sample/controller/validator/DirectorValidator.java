package com.mvc.spring.sample.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mvc.spring.sample.model.Movie;

@Component
public class DirectorValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Movie.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Movie movie = (Movie) target;
		
		String director = movie.getDirector();
		
		if(director != null && director.split(" ").length < 2) {
			errors.rejectValue("director", "director_error", "At least 2 words");
		}
		
	}

}
