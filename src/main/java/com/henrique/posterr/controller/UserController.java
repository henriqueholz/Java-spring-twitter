package com.henrique.posterr.controller;

import com.henrique.posterr.Responses.UserProfileResponse;
import com.henrique.posterr.dao.PostRepository;
import com.henrique.posterr.dao.UserRepository;
import com.henrique.posterr.model.User;
import com.henrique.posterr.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserProfileResponse userProfileResponse;
    @Autowired
    private UserRepository R_User;
    @Autowired
    private PostRepository R_Post;
    @Autowired
    private DateUtil dateUtil;

    @GetMapping(path = "user/{user_id}", produces = "application/json")
    public ResponseEntity<Object> getUserData(
            @PathVariable("user_id") long user_id
    ) throws Exception
    {
        User user = R_User.findByUserid(user_id);
        userProfileResponse.setUsername(user.getUsername());
        userProfileResponse.setJoinedDate(dateUtil.currentDate(user.getUser_joined_at()));
        userProfileResponse.setFollowsNumber(R_User.getUserFollowingNumber(user_id));
        userProfileResponse.setFollowingNumber(R_User.getUserFollowerNumber(user_id));
        userProfileResponse.setUserPostsNumber(R_Post.countDailyPostsByUser(user_id));

        return new ResponseEntity<>(userProfileResponse.getBodyResponse(), HttpStatus.OK);
    }
}