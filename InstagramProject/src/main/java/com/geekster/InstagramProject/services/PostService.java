package com.geekster.InstagramProject.services;

import com.geekster.InstagramProject.models.Post;
import com.geekster.InstagramProject.models.User;
import com.geekster.InstagramProject.repositories.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    IPostRepo iPostRepo;

    @Autowired
    UserService userService;

    public String createPostData(String postData,Long userId) {
        User user = userService.findUserById(userId);

        Post post = new Post(postData,user);

        iPostRepo.save(post);
        return "saved succesfully.!!!";
    }

    public Post getPostByUserId(Long userId) {
        User user = userService.findUserById(userId);
        Post post = iPostRepo.findByUser(user);
        return post;
    }
}
