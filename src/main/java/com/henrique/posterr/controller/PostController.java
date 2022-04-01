package com.henrique.posterr.controller;

import com.henrique.posterr.service.PostService;
import com.henrique.posterr.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping(path = "/post/create", produces = "application/json")
    public ResponseEntity<Object> createPost(@RequestBody Post newPost)
    {
        Post savedPost = postService.createPost(newPost);
        Map<String,Object> response = new HashMap<>();
        response.put("post", savedPost);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
