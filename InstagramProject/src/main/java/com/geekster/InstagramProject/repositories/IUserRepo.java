package com.geekster.InstagramProject.repositories;

import com.geekster.InstagramProject.models.User;
import org.springframework.data.repository.ListCrudRepository;

public interface IUserRepo extends ListCrudRepository<User,Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
