package org.launchcode.trailerqueen.service;

import org.launchcode.trailerqueen.models.Park;
import org.launchcode.trailerqueen.models.User;
import org.launchcode.trailerqueen.repositories.ParkRepository;
import org.launchcode.trailerqueen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParkServiceImp implements ParkService {

    @Autowired
    ParkRepository parkRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isParkAlreadyPresent(Park park) {
        boolean isParkAlreadyPresent = false;
        Park existingPark = parkRepository.findByCode(park.getCode());
        if(existingPark != null){
            isParkAlreadyPresent = true;
        }

        return isParkAlreadyPresent;
    }

    @Override
    public void savePark(Park park) {
        parkRepository.save(park);
    }

    @Override
    public void favePark(Park park) {

        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }

        User user = userRepository.findByEmail(currentUserName);
        park.setUsers(new HashSet<User>(Arrays.asList(user)));
        parkRepository.save(park);
    }

}
