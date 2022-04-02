package com.henrique.posterr.dao;

import com.henrique.posterr.model.Follower;
import com.henrique.posterr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follower, Long> {
//    @Query("SELECT * FROM FOLLOWER WHERE FOLLOWER = ?1")
//    List<Follower> findTweetsThatUserFollows(User user);

    @Query( value="SELECT * FROM FOLLOWER WHERE FOLLOWING = ?1 and FOLLOWER = ?2",
            nativeQuery = true
    )
    Optional<Follower> findByFollowingAndFollower(User following, User follower);
}
