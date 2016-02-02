package com.mvc.spring.sample.controller;

import com.mvc.spring.sample.model.Movie;
import com.mvc.spring.sample.model.User;
import com.mvc.spring.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String newForm(Model model) {
        model.addAttribute("user", new User());
        return "users/userForm";
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute @Valid User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "users/register";
        }

        userService.create(user);

        return "redirect:/users";
    }
    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getUsers(Model model) {

        Iterable<User> users = userService.findAll();

        model.addAttribute("userList", users);
        return "users/userList";
    }

}
