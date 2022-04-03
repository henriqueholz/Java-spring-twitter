package com.henrique.posterr;

import com.henrique.posterr.dao.FollowRepository;
import com.henrique.posterr.dao.PostRepository;
import com.henrique.posterr.dao.UserRepository;
import com.henrique.posterr.model.Post;
import com.henrique.posterr.model.PosterrUser;
import com.henrique.posterr.service.FollowService;
import com.henrique.posterr.service.PostService;
import com.henrique.posterr.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class PosterrServiceTest {

    @InjectMocks
    PostService postService;
    @InjectMocks
    FollowService followService;
    @Mock
    PostRepository R_Post;
    @Mock
    UserRepository R_User;
    @Mock
    FollowRepository R_Follow;
    @Mock
    DateUtil dateUtil;

    @Test
    void create_post_should_return_a_post() throws Exception {
        PosterrUser loggedInUser = new PosterrUser();
        loggedInUser.setUsername("user test");
        loggedInUser.setUser_id(1);
        Post newPost = new Post();
        newPost.setPost_message("unit testing");
        Mockito.when(R_User.findByUserid(1)).thenReturn(loggedInUser);
        Mockito.when(R_Post.save(any(Post.class))).thenReturn(newPost);
        Mockito.when(dateUtil.currentTimestamp()).thenReturn(new Timestamp(System.currentTimeMillis()));

        Post savedPost = postService.createPost(loggedInUser, newPost);
        Assert.isTrue(savedPost instanceof Post);
        Assert.isTrue("unit testing".equals(newPost.getPost_message()));
        Assert.isTrue(loggedInUser.equals(newPost.getPost_user_id()));
    }

    @Test
    void follow_an_nonexistent_user_should_throw_exception() throws Exception
    {
        PosterrUser loggedInUser = new PosterrUser();
        loggedInUser.setUser_id(1);
        loggedInUser.setUsername("logged user");
        Mockito.when(R_User.save(loggedInUser)).thenReturn(loggedInUser);

        int nonexistentUserId = 9999;

        Assertions.assertThrows(Exception.class,()-> followService.followUser(loggedInUser, (long) nonexistentUserId) );
    }


}
