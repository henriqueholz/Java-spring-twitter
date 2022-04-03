package com.henrique.posterr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Follower {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "following")
    private PosterrUser following;

    @ManyToOne
    @JoinColumn(name = "follower")
    private PosterrUser follower;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PosterrUser getFollowing() {
        return following;
    }

    public void setFollowing(PosterrUser following) {
        this.following = following;
    }

    public PosterrUser getFollower() {
        return follower;
    }

    public void setFollower(PosterrUser follower) {
        this.follower = follower;
    }
}
