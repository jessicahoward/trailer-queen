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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){

        model.addAttribute("title", "Welcome");
        return "user/welcome";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String displayRegisterForm(Model model) {

        model.addAttribute("title", "Create Account");
        model.addAttribute(new User());
        return "user/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processRegisterForm(@ModelAttribute @Valid User newUser,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Account");
//            model.addAttribute(new User());
            return "user/register";
        }

        userDao.save(newUser);
        return "redirect:";
    }

//    @RequestMapping(value = "login", method = RequestMethod.GET)
//    public String displayLoginForm(Model model) {
//
//        model.addAttribute("title", "Login");
//        return "user/login";
//    }


}
