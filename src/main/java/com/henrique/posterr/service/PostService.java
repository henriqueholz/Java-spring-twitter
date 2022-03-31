package com.henrique.posterr.service;

import com.henrique.posterr.dao.PostRepository;
import com.henrique.posterr.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class PostService {
    @Autowired
    PostRepository R_Post;

    public Post createPost(Post newPost)
    {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        newPost.setPost_created_at(currentTimestamp);
        return R_Post.save(newPost);
    }
}
