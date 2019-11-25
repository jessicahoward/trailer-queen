package org.launchcode.trailerqueen.service;

import org.launchcode.trailerqueen.models.User;

public interface UserService {

    public void saveUser(User user);
    public boolean isUserAlreadyPresent(User user);

}
