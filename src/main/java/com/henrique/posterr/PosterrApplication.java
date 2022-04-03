package com.henrique.posterr;

import com.henrique.posterr.dao.PostRepository;
import com.henrique.posterr.dao.UserRepository;
import com.henrique.posterr.model.Post;
import com.henrique.posterr.model.PosterrUser;
import com.henrique.posterr.util.DateUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PosterrApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosterrApplication.class, args);
	}

	// Adding some mock users and posts to help the testing
	@Bean
	CommandLineRunner init (UserRepository userRepository, PostRepository postRepository){
		return args -> {
			List<String> names = Arrays.asList("JoeBiden", "DonaldTrump", "BarackObama", "GeorgeBush");
			for (String name : names) {
				PosterrUser user = new PosterrUser();
				user.setUsername(name);
				user.setUser_joined_at(new Timestamp(System.currentTimeMillis()));
				userRepository.save(user);
				Post post = new Post();
				post.setPost_user(user);
				post.setPost_created_at(new Timestamp(System.currentTimeMillis()));
				post.setPost_message(user.getUsername() + " says hi");
				postRepository.save(post);
			}
		};
	}

}
