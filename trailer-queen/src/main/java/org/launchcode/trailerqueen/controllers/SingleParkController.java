package org.launchcode.trailerqueen.controllers;

import org.launchcode.trailerqueen.models.Park;
import org.launchcode.trailerqueen.models.User;
import org.launchcode.trailerqueen.repository.ParkRepository;
import org.launchcode.trailerqueen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SingleParkController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkRepository parkRepository;

    @RequestMapping(value = "park-view", method = RequestMethod.POST)
    public String favIndividualPark(Model model, @RequestParam int code) {

        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }

        User thisUser = userRepository.findByEmail(currentUserName);
        System.out.println(">>>>>>>>>>>>>>>>>> " + thisUser.getId());

        Park thisPark = parkRepository.findByCode(code);
        System.out.println("<<<<<<<<<<<<<<<<<< " + thisPark.getId());



        return "redirect:/home";
    }
}
