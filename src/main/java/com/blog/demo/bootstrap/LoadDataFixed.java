package com.blog.demo.bootstrap;

import com.blog.demo.entities.*;
import com.blog.demo.repositories.*;
import com.blog.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

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

    @Autowired
    private ImageRepository imageRepository;

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
    private Post adminPost = new Post("This is Admin post", "Tytul 1");
    private Post adminPost2 = new Post("TRIATHLON", "Tytul2");
    private Post adminPost3 = new Post("IRONMAN", "Tytul3");
    private Post adminPost4 = new Post("BOBOMAN", "Tytul 4");
    private Post adminPost5 = new Post("BOBOMAN", "Tytul 4");
    private Post adminPostSecond = new Post("Second Post", " Tytul 6");
    private UserDetail adminDetails = new UserDetail("About admin");
    private Comment firstComment = new Comment("Nice content");
    private Comment secondComment = new Comment("Bad content");
    private Set<Post> userPosts = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();






    @Override
    public void run(String... args) throws Exception {

        String adminPassword = "{bcrypt}" + secret.encode("Password28");

        User admin = new User("admin@gmail.com", adminPassword, "bigos", true);
        admin.setUserDetail(adminDetails);
        admin.setUserCommentCount(0);



       /* adminPost.setPostTopics("Java");
        adminPost2.setPostTopics("Java");
        adminPost3.setPostTopics("Triathlon");
        adminPost4.setPostTopics("Triathlon");*/

        roleRepository.save(adminRole);

        admin.setConfirmPassword(adminPassword);

        userRepository.save(admin);
        admin.addRole(adminRole);



      //  comments.add(firstComment);
      //  comments.add(secondComment);
     //   userPosts.add(adminPost);


        postRepository.saveAll(userPosts);

      // firstComment.setPost(adminPost);
      // secondComment.setPost(adminPost);

        adminPost.setUser(admin);
        adminPostSecond.setUser(admin);


        admin.addRole(adminRole);

        userRepository.save(admin);
        roleRepository.save(adminRole);


        adminPost.setComments(comments);
        firstComment.setUser(admin);

       // commentRepository.saveAll(comments);
       // commentRepository.save(firstComment);
      //  postRepository.save(adminPost);
       // postRepository.save(adminPost2);
        //postRepository.saveAll(userPosts);
        //postRepository.save(adminPost2);
       // postRepository.save(adminPost3);
       // postRepository.save(adminPost4);
        //postRepository.save(adminPost5);
        // userEntityService.registerNewUser(admin);
        // commentRepository.delete(firstComment);
        //  postRepository.delete(adminPost); http://www.javadream.in/how-to-upload-file-in-spring-boot-using-form/



    }
}

















