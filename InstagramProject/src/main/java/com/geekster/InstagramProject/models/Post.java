package com.geekster.InstagramProject.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private String postData;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post(String postData, User user) {
        this.postData = postData;
        this.user = user;
        this.createdDate = new Timestamp(new Date().getTime());
        this.updatedDate = new Timestamp(new Date().getTime());
    }
}
