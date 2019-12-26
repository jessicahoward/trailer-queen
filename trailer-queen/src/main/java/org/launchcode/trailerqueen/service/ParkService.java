package org.launchcode.trailerqueen.service;

import org.launchcode.trailerqueen.models.Park;

public interface ParkService {

    public void savePark(Park park);
    public void favePark(Park park);
    public boolean isParkAlreadyPresent(Park park);

}
