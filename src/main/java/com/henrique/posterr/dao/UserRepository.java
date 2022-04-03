package com.henrique.posterr.dao;

import com.henrique.posterr.model.PosterrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<PosterrUser, Long> {
    PosterrUser findByUserid(long userid);

    @Query( value="SELECT COUNT(*) FROM FOLLOWER WHERE FOLLOWER = ?1",
            nativeQuery = true)
    Long getUserFollowerNumber(Long userid);

    @Query( value="SELECT COUNT(*) FROM FOLLOWER WHERE FOLLOWING = ?1",
            nativeQuery = true)
    Long getUserFollowingNumber(Long userid);
}
