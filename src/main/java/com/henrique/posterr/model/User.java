package com.henrique.posterr.model;
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
public class User {
    @Id
    @GeneratedValue
    private long userid;

    @Size(max = 14)
//    @Pattern(regexp = "[a-zA-Z0-9 ]")
    private String username;

    // TODO: format "March 25, 2021"
    private Timestamp joined_at;

    @OneToMany(mappedBy = "following")
    private List<Follower> following;

    @OneToMany(mappedBy = "followers")
    private List<Follower> followers;

    @OneToMany(mappedBy = "post_user")
    private List<Post> post_user;

    @OneToMany(mappedBy = "repost_user")
    private List<Post> repost_user;

    public long getUser_id() {
        return userid;
    }

    public void setUser_id(long user_id) {
        this.userid = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getUser_joined_at() {
        return joined_at;
    }

    public void setUser_joined_at(Timestamp user_joined_at) {
        this.joined_at = user_joined_at;
    }
}
