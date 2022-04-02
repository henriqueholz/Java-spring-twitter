package com.henrique.posterr.dao;

import com.henrique.posterr.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostid(long postid);

    @Query( value="SELECT * FROM POST WHERE (POST_USER = 1 OR REPOST_USER = 1) AND (POST_CREATED_AT >= NOW() - INTERVAL 1 DAY)",
            nativeQuery = true
    )
    Long getLoggedUserPostsPerDay();
}
