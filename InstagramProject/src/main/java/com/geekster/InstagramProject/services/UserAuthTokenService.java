package com.geekster.InstagramProject.services;

import com.geekster.InstagramProject.models.User;
import com.geekster.InstagramProject.models.UserAuthenticationToken;
import com.geekster.InstagramProject.repositories.IUserAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthTokenService {

    @Autowired
    IUserAuthTokenRepo iUserAuthTokenRepo;

    public void saveAuthorisedToken(UserAuthenticationToken userAuthenticationToken) {
        iUserAuthTokenRepo.save(userAuthenticationToken);
    }

    public UserAuthenticationToken getUserAuthenticationTokenByUser(User user) {
        return iUserAuthTokenRepo.findByUser(user);
    }
}
