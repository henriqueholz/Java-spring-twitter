package com.henrique.posterr.service;

import com.henrique.posterr.dao.FollowRepository;
import com.henrique.posterr.dao.UserRepository;
import com.henrique.posterr.model.Follower;
import com.henrique.posterr.model.PosterrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    @Autowired
    private UserRepository R_User;
    @Autowired
    FollowRepository R_Follow;

    public void followUser(PosterrUser loggedUser, Long user_id) throws Exception
    {
        PosterrUser followedUser = R_User.findById(user_id).orElse(null);
        if (followedUser == null)
        {
            throw new Exception("User not found");
        }
        if (loggedUser.getUser_id() == user_id)
        {
            throw new Exception("You can not follow yourself");
        }
        Follower userFollow = R_Follow.findByFollowingAndFollower(followedUser, loggedUser).orElse(null);
        if (userFollow != null) {
            throw new Exception("You already follows " + followedUser.getUsername());
        }
        Follower follower = new Follower();
        follower.setFollower(loggedUser);
        follower.setFollowing(followedUser);
        R_Follow.save(follower);
    }

    public void unfollowUser(PosterrUser loggedUser, Long user_id) throws Exception
    {
        PosterrUser unfollowedUser = R_User.findById(user_id).orElse(null);
        if(unfollowedUser == null)
        {
            throw new Exception("User not found");
        }

        Follower follower = R_Follow.findByFollowingAndFollower(unfollowedUser, loggedUser).orElse(null);
        if(follower == null)
        {
            throw new Exception("User not following " + unfollowedUser.getUsername());
        }
        R_Follow.delete(follower);
    }
}
