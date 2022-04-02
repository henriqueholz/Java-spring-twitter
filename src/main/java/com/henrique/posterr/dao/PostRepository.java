package com.henrique.posterr.dao;

import com.henrique.posterr.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostid(long postid);

    @Query( value="SELECT TOP ?1 * FROM POST " +
            "ORDER BY POST_CREATED_AT DESC",
            nativeQuery = true)
    List<Post> getAllPosts(int numberOfPosts);

    @Query( value="SELECT TOP ?1 * FROM POST WHERE (" +
            "POST_USER = ?2 OR " +
            "REPOST_USER = ?2 OR " +
            "POST_USER IN (SELECT FOLLOWING FROM FOLLOWER WHERE FOLLOWER = ?2)  OR " +
            "REPOST_USER IN (SELECT FOLLOWING FROM FOLLOWER WHERE FOLLOWER = ?2) ) " +
            "ORDER BY POST_CREATED_AT DESC",
            nativeQuery = true)
    List<Post> findPostsByUser(int numberOfPosts, Long userid);

    @Query( value="SELECT COUNT(1) FROM POST WHERE (POST_USER = ?1 OR REPOST_USER = ?1) AND POST_CREATED_AT > NOW() - INTERVAL 1 DAY;",
            nativeQuery = true)
    Long countDailyPostsByUser(Long userid);
}
