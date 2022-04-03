package com.henrique.posterr.dao;

import com.henrique.posterr.model.Follower;
import com.henrique.posterr.model.PosterrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follower, Long> {

    @Query( value="SELECT * FROM FOLLOWER WHERE FOLLOWING = ?1 and FOLLOWER = ?2",
            nativeQuery = true
    )
    Optional<Follower> findByFollowingAndFollower(PosterrUser following, PosterrUser follower);
}
