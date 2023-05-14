package com.geekster.InstagramProject.controllers;

import com.geekster.InstagramProject.models.Post;
import com.geekster.InstagramProject.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/postData/{postData}/userId/{userId}")
    public String createPostData(@PathVariable String postData,@PathVariable Long userId) {
        return postService.createPostData(postData,userId);
    }

    @GetMapping("/post/{userId}")
    public Post getPostByUserId(@PathVariable Long userId) {
        return postService.getPostByUserId(userId);
    }
}
