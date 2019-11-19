package org.launchcode.trailerqueen.controllers;

import org.launchcode.trailerqueen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.launchcode.trailerqueen.models.data.UserDao;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "add-user", method = RequestMethod.GET)
    public String displayCreateUserForm(Model model) {

        model.addAttribute("title", "Create Account");
        model.addAttribute(new User());
        return "user/addUser";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {

        model.addAttribute("title", "Login");
        return "user/login";
    }


}
