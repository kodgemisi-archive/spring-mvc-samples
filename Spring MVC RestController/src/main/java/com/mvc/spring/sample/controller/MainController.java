package com.mvc.spring.sample.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Aykut on 30.01.2016.
 */
@RestController
public class MainController {

    @RequestMapping("/greeting")
    public String greetingQueryParam(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return String.format("Greeting, %s!", name);
    }

    @RequestMapping("/hello/{name}")
    public String greetingPath(@PathVariable String name) {
        return String.format("Hello, %s!", name);
    }

    @RequestMapping("/")
    public String index() {
        return "You made it!!";
    }

}
