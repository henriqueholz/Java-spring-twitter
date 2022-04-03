package com.henrique.posterr.controller;

import com.henrique.posterr.Responses.ApiResponse;
import com.henrique.posterr.dao.UserRepository;
import com.henrique.posterr.model.PosterrUser;
import com.henrique.posterr.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class FollowController {

    @Autowired
    private ApiResponse apiResponse;
    @Autowired
    private UserRepository R_User;
    @Autowired
    FollowService followService;

    public PosterrUser loggedUser;

    public void getLoggedUser() {
        long mockedUserId = 1;
        loggedUser = R_User.findByUserid(mockedUserId);
    }

    @GetMapping(path = "follow/{user_id}", produces = "application/json")
    public ResponseEntity<Object> followUser(
            @PathVariable("user_id") long user_id
    ) throws Exception
    {
        if (loggedUser == null) {
            getLoggedUser();
        }
        followService.followUser(loggedUser, user_id);
        apiResponse.setMessage("Follow added with success");
        apiResponse.setData(user_id);

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.CREATED);
    }

    @GetMapping(path = "unfollow/{user_id}", produces = "application/json")
    public ResponseEntity<Object> unfollowUser(
            @PathVariable("user_id") long user_id
    ) throws Exception
    {
        if (loggedUser == null) {
            getLoggedUser();
        }
        followService.unfollowUser(loggedUser, user_id);
        apiResponse.setMessage("Follow removed witch success");
        apiResponse.setData(user_id);

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }
}
