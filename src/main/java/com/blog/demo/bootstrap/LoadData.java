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
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by damiass on Sep, 2019
 */
@Slf4j
@Component
public class LoadData implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(CommandLineRunner.class);

    private final UserService userEntityService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PostRepository postRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final CommentRepository commentRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public LoadData(UserService userEntityService,
                                 UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PostRepository postRepository,
                                 UserDetailsRepository userDetailsRepository,
                                 CommentRepository commentRepository) {
        this.userEntityService = userEntityService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.postRepository = postRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.commentRepository = commentRepository;
    }

    // https://github.com/reljicd/spring-boot-blog/blob/master/src/main/java/com/reljicd/model/Post.java
    String secret = "{bcrypt}" + encoder.encode("damian28");
    private Role newUserRole = new Role("ROLE_USER");
    private Role adminRole = new Role("ROLE_ADMIN");
    private Post newPost = new Post("Content of post");
    private Post newPost2 = new Post("Content of post2 huehue :> Cu@ban");
    private User newUser = new User("user@gmail.com", secret, "kaktusx22");
    private UserDetail userDetail = new UserDetail("About user");
    private Comment comment1 = new Comment("Very good");
    private Comment comment2 = new Comment("Bad bad bad");

    private Set<Post> userPosts = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();


    @Override
    public void run(String... args)  {

        BCryptPasswordEncoder loginEncoder = new BCryptPasswordEncoder();
        String passForLogin = "{bcrypt}" + loginEncoder.encode("password");
        User loginUser = new User("admin@gmail.com", passForLogin, "bigos");
        roleRepository.save(adminRole);
        loginUser.addRole(adminRole);



        userRepository.save(loginUser);



        saveNewUserEntity();
        setAndSaveDetailsForUser();
        addPostsToHashSetAndSaveThemInRepository();
        setNewPostToOneUserEntity();

        addSingleUserRoleAndSaveRole();


        comments.add(comment1);
        comments.add(comment2);

        comment1.setPost(newPost);
        comment1.setUser(newUser);

        comment2.setPost(newPost2);
        comment2.setUser(newUser);

        newPost.setComments(comments);

        commentRepository.saveAll(comments);
        postRepository.saveAll(userPosts);


        saveSinglePostToUser();
        saveNewUserEntity();




    }



    private void addSingleUserRoleAndSaveRole() {
        newUser.addRole(newUserRole);
        roleRepository.save(newUserRole);
    }
    private void saveSinglePostToUser() {
        postRepository.save(newPost);
        postRepository.save(newPost2);

    }
    private void saveNewUserEntity() {
        userEntityService.registerNewUser(newUser);

    }
    private void setAndSaveDetailsForUser() {
        newUser.setUserDetail(userDetail);
        userDetailsRepository.save(userDetail);
    }
    private void addPostsToHashSetAndSaveThemInRepository() {
        userPosts.add(newPost);
        userPosts.add(newPost2);
        postRepository.saveAll(userPosts);
    }
    private void setNewPostToOneUserEntity() {
        newPost.setUser(newUser);
        newPost2.setUser(newUser);
    }

}
