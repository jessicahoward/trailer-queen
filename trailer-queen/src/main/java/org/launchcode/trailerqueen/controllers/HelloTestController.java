package org.launchcode.trailerqueen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloTestController {

    @RequestMapping(value = "hello")
    @ResponseBody
    public String index() {
        return "Hello Cruel World";
    }
}
