package com.geekster.InstagramProject.repositories;

import com.geekster.InstagramProject.models.Post;
import com.geekster.InstagramProject.models.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

public interface IPostRepo extends ListCrudRepository<Post,Long> {
    Post findByPostData(String postData);

    Post findByUser(User user);
}
