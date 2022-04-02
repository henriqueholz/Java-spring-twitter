package com.henrique.posterr.controller;

import com.henrique.posterr.Responses.ApiResponse;
import com.henrique.posterr.dao.PostRepository;
import com.henrique.posterr.dao.UserRepository;
import com.henrique.posterr.model.User;
import com.henrique.posterr.service.PostService;
import com.henrique.posterr.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserRepository R_User;
    @Autowired
    private PostRepository R_Post;
    @Autowired
    private ApiResponse apiResponse;

    public User loggedUser;

    public void getLoggedUser() {
        long mockedUserId = 1;
        loggedUser = R_User.findByUserid(mockedUserId);
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<Object> createPost(
            @RequestBody Post newPost) throws Exception {
        if (loggedUser == null) {
            getLoggedUser();
        }
        Post savedPost = postService.createPost(loggedUser, newPost);
        apiResponse.setMessage("Post created with success");
        apiResponse.setData(savedPost);

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.CREATED);
    }

    @GetMapping(path = "/repost/{post_id}", produces = "application/json")
    public ResponseEntity<Object> repost(
            @PathVariable("post_id") String postId) throws Exception {
        if (loggedUser == null) {
            getLoggedUser();
        }
        Post repostedPost = postService.repostPost(loggedUser, postId);
        apiResponse.setMessage("Post reposted with success");
        apiResponse.setData(repostedPost);

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }

    @PostMapping(path = "/quote/{post_id}", produces = "application/json")
    public ResponseEntity<Object> quote(
            @PathVariable("post_id") String postId,
            @RequestBody Post quotePost) throws Exception {
        if (loggedUser == null) {
            getLoggedUser();
        }
        Post repostedPost = postService.quotePost(loggedUser, postId, quotePost);
        apiResponse.setMessage("Post quoted with success");
        apiResponse.setData(repostedPost);

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }

    @GetMapping(path = "/userfeed/{number_of_posts}", produces = "application/json")
    public ResponseEntity<Object> getFeed(
            @PathVariable("number_of_posts") int numberOfPosts
    )
    {
        if (loggedUser == null) {
            getLoggedUser();
        }
        List<Post> userFeed = postService.getFeedByUser(loggedUser, numberOfPosts);
        apiResponse.setMessage("User Feed");
        apiResponse.setData(userFeed);

        return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
    }

    @GetMapping(path = "/feed/{number_of_posts}", produces = "application/json")
    public ResponseEntity<Object> getFullFeed(
            @PathVariable("number_of_posts") int numberOfPosts
    )
    {
        if (loggedUser == null) {
            getLoggedUser();
        }
        List<Post> userFeed = postService.getFullFeed(numberOfPosts);
        apiResponse.setMessage("Full Feed");
        apiResponse.setData(userFeed);

        return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
    }
}
