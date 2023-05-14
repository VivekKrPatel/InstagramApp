package com.geekster.InstagramProject.repositories;

import com.geekster.InstagramProject.models.User;
import com.geekster.InstagramProject.models.UserAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserAuthTokenRepo extends JpaRepository<UserAuthenticationToken,Long> {
    UserAuthenticationToken findByUser(User user);
}
