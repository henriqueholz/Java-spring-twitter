package com.henrique.posterr;

import com.henrique.posterr.dao.PostRepository;
import com.henrique.posterr.dao.UserRepository;
import com.henrique.posterr.model.Post;
import com.henrique.posterr.model.PosterrUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class PosterrApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosterrApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	// Adding some mock users and posts to help the testing
	@Bean
	CommandLineRunner init (UserRepository userRepository, PostRepository postRepository){
		return args -> {
			if (userRepository.findByUserid(1) == null) {
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
			}
		};
	}

}
