package com.henrique.posterr.dao;

import com.henrique.posterr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserid(long userid);

    @Query( value="SELECT COUNT(1) FROM FOLLOWER WHERE FOLLOWER = ?1",
            nativeQuery = true)
    Long getUserFollowerNumber(Long userid);

    @Query( value="SELECT COUNT(1) FROM FOLLOWER WHERE FOLLOWING = ?1",
            nativeQuery = true)
    Long getUserFollowingNumber(Long userid);
}
