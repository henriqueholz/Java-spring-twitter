package com.henrique.posterr.dao;

import com.henrique.posterr.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    User findByUsername(String username);
//    List<User> findByUsernameNot(String username);
}
