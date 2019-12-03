package org.launchcode.trailerqueen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultsController {

    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String displayResults(@RequestParam String code) {

        System.out.println(code);

        return "results";
    }
}




