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
public class HelloTestController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "hello")
    @ResponseBody
    public String hello() {
        return "Hello Cruel World";
    }

    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayCreateUserForm(Model model) {

        model.addAttribute("title", "Create Account");
        model.addAttribute(new User());
        return "user/index";
    }


}
