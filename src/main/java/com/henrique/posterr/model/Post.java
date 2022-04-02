package com.henrique.posterr.model;

import com.henrique.posterr.util.EnumPostType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private long postid;

    private EnumPostType postType;

    @Size(max = 777)
    private String post_message;

    @Size(max = 777)
    private String quote_message;

    private Timestamp post_created_at;

    @ManyToOne
    @JoinColumn(name = "post_user")
    private User post_user;

    @ManyToOne
    @JoinColumn(name = "repost_user")
    private User repost_user;

    private long original_postid;

    public long getPost_id() {
        return postid;
    }

    public void setPost_id(long post_id) {
        this.postid = post_id;
    }

    public String getPost_message() {
        return post_message;
    }

    public void setPost_message(String post_message) {
        this.post_message = post_message;
    }

    public String getQuote_message() {
        return quote_message;
    }

    public void setQuote_message(String quote_message) {
        this.quote_message = quote_message;
    }

    public Timestamp getPost_created_at() {
        return post_created_at;
    }

    public void setPost_created_at(Timestamp post_created_at) {
        this.post_created_at = post_created_at;
    }

    public User getPost_user_id() {
        return post_user;
    }

    public void setPost_user(User user) {
        this.post_user = user;
    }

    public void setRepost_user(User user) {
        this.repost_user = user;
    }

    public void setOriginalPost_id(long original_postidpost_id) {
        this.original_postid = original_postidpost_id;
    }

    public void setPostType(EnumPostType postType) {
        this.postType = postType;
    }


}
