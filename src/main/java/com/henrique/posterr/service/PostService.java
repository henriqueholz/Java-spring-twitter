package com.henrique.posterr.service;

import com.henrique.posterr.dao.PostRepository;
import com.henrique.posterr.dao.UserRepository;
import com.henrique.posterr.enumutil.EnumPostType;
import com.henrique.posterr.model.Post;
import com.henrique.posterr.model.PosterrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository R_Post;
    @Autowired
    UserRepository R_User;

    public Post createPost(PosterrUser loggedUser, Post newPost) throws Exception {
        if (R_Post.countDailyPostsByUser(loggedUser.getUser_id()) > 5) {
            throw new Exception("A user is not allowed to post more than 5 posts in one day (including reposts and quote posts)");
        }
        newPost.setPost_user(loggedUser);
        newPost.setPost_created_at(new Timestamp(System.currentTimeMillis()));
        newPost.setPostType(EnumPostType.TYPE1);
        newPost.setOriginalPost_id(newPost.getPost_id());
        return R_Post.save(newPost);
    }

    public Post repostPost(PosterrUser loggedUser, String PostId) throws Exception {
        if (R_Post.countDailyPostsByUser(loggedUser.getUser_id()) > 5) {
            throw new Exception("A user is not allowed to post more than 5 posts in one day (including reposts and quote posts)");
        }
        Post repostedPost = R_Post.findByPostid(Long.parseLong(PostId));
        if (repostedPost == null) {
            throw new Exception("Post not found");
        }
        if (repostedPost.getPost_user_id().getUser_id() == loggedUser.getUser_id()) {
            throw new Exception("A user can only repost other users' posts");
        }
        Post newPost = new Post();
        newPost.setPost_user(repostedPost.getPost_user_id());
        newPost.setRepost_user(loggedUser);
        newPost.setPostType(EnumPostType.TYPE2);
        newPost.setOriginalPost_id(Long.parseLong(PostId));
        newPost.setPost_message(repostedPost.getPost_message());
        newPost.setPost_created_at(new Timestamp(System.currentTimeMillis()));
        return R_Post.save(newPost);
    }

    public Post quotePost(PosterrUser loggedUser, String PostId, Post newPost) throws Exception {
        if (R_Post.countDailyPostsByUser(loggedUser.getUser_id()) > 5) {
            throw new Exception("A user is not allowed to post more than 5 posts in one day (including reposts and quote posts)");
        }
        Post quotedPost = R_Post.findByPostid(Long.parseLong(PostId));
        if (quotedPost == null) {
            throw new Exception("Post not found");
        }
        if (quotedPost.getPost_user_id().getUser_id() == loggedUser.getUser_id()) {
            throw new Exception("A user can only repost other users' posts");
        }
        Post newQuotePost = new Post();
        newQuotePost.setPost_user(quotedPost.getPost_user_id());
        newQuotePost.setRepost_user(loggedUser);
        newQuotePost.setPostType(EnumPostType.TYPE3);
        newQuotePost.setOriginalPost_id(Long.parseLong(PostId));
        newQuotePost.setPost_message(quotedPost.getPost_message());
        newQuotePost.setPost_created_at(new Timestamp(System.currentTimeMillis()));
        newQuotePost.setQuote_message(newPost.getPost_message());
        return R_Post.save(newQuotePost);
    }

    public List<Post> getFeedByUser(PosterrUser loggedUser, int numberOfPosts)
    {
        List<Post> userPosts = R_Post.findPostsByUser(numberOfPosts, loggedUser.getUser_id());
        return userPosts;
    }

    public List<Post> getFullFeed(int numberOfPosts)
    {
        List<Post> userPosts = R_Post.getAllPosts(numberOfPosts);
        return userPosts;
    }
}
