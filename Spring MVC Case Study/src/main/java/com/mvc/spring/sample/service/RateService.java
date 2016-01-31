package com.mvc.spring.sample.service;

import com.mvc.spring.sample.model.Movie;
import com.mvc.spring.sample.model.Rate;
import com.mvc.spring.sample.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;

    public void save(Rate rate) {
        rateRepository.save(rate);
    }



}
