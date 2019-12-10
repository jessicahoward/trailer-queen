package org.launchcode.trailerqueen.service;

import org.launchcode.trailerqueen.models.Park;
import org.launchcode.trailerqueen.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkServiceImp implements ParkService {

    @Autowired
    ParkRepository parkRepository;

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
}
