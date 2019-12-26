package org.launchcode.trailerqueen.controllers;

import org.launchcode.trailerqueen.models.Park;
import org.launchcode.trailerqueen.repositories.ParkRepository;
import org.launchcode.trailerqueen.repositories.UserRepository;
import org.launchcode.trailerqueen.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SingleParkController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    ParkService parkService;

    @RequestMapping(value = "park-view", method = RequestMethod.POST)
    public String favIndividualPark(@RequestParam int code) {

        Park thisPark = parkRepository.findByCode(code);
        parkService.favePark(thisPark);

        return "redirect:/home";
    }
}
