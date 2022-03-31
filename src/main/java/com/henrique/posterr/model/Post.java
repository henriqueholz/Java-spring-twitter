package com.henrique.posterr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.security.Timestamp;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private long postid;

    @Size(max = 777)
    private String post_message;

    private Timestamp post_created_at;

    private Timestamp post_updated_at;

    @ManyToOne
    @JoinColumn(name = "post_user")
    private User post_user;

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

    public Timestamp getPost_created_at() {
        return post_created_at;
    }

    public void setPost_created_at(Timestamp post_created_at) {
        this.post_created_at = post_created_at;
    }

    public Timestamp getPost_updated_at() {
        return post_updated_at;
    }

    public void setPost_updated_at(Timestamp post_updated_at) {
        this.post_updated_at = post_updated_at;
    }

    public User getPost_user_id() {
        return post_user;
    }

    public void setPost_user_id(User user) {
        this.post_user = user;
    }
}
