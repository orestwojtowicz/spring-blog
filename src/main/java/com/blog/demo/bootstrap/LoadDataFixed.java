package com.blog.demo.bootstrap;

import com.blog.demo.entities.*;
import com.blog.demo.repositories.*;
import com.blog.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by damiass on Oct, 2019
 */

@Slf4j
@Component
public class LoadDataFixed implements CommandLineRunner  {
    private final Logger logger = LoggerFactory.getLogger(CommandLineRunner.class);

    private final UserService userEntityService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PostRepository postRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final CommentRepository commentRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // private final BCryptPasswordEncoder encoder;

    public LoadDataFixed(UserService userEntityService,
                    UserRepository userRepository,
                    RoleRepository roleRepository,
                    PostRepository postRepository,
                    UserDetailsRepository userDetailsRepository,
                    CommentRepository commentRepository
    ) {
        this.userEntityService = userEntityService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.postRepository = postRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.commentRepository = commentRepository;

    }

    private BCryptPasswordEncoder secret = new BCryptPasswordEncoder();
    private Role adminRole = new Role("ROLE_ADMIN");
    private Post adminPost = new Post("This is Admin post");
    private Post adminPostSecond = new Post("Second Post");
    private UserDetail adminDetails = new UserDetail("About admin");
    private Comment firstComment = new Comment("Nice content");
    private Comment secondComment = new Comment("Bad content");
    private Set<Post> userPosts = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();

    @Override
    public void run(String... args) throws Exception {

        String adminPassword = "{bcrypt}" + secret.encode("Password28@");

        User admin = new User("admin@gmail.com", adminPassword, "bigos", true);
        roleRepository.save(adminRole);

        admin.setConfirmPassword(adminPassword);
        userRepository.save(admin);
        admin.addRole(adminRole);

        comments.add(firstComment);
        comments.add(secondComment);
        userPosts.add(adminPost);
        userPosts.add(adminPostSecond);

        commentRepository.saveAll(comments);
        postRepository.saveAll(userPosts);

        firstComment.setPost(adminPost);
        secondComment.setPost(adminPost);

        adminPost.setUser(admin);
        adminPostSecond.setUser(admin);

        adminPost.setComments(comments);

        commentRepository.saveAll(comments);
        postRepository.saveAll(userPosts);
       // userEntityService.registerNewUser(admin);


       // commentRepository.delete(firstComment);


      //  postRepository.delete(adminPost);

      Optional<Post> post =  postRepository.findById(5L);

      //postRepository.delete(post.get());

      commentRepository.delete(firstComment);



    }
}

















