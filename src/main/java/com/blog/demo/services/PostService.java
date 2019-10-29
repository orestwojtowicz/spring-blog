package com.blog.demo.services;


import com.blog.demo.entities.Post;
import com.blog.demo.entities.User;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by damiass on Sep, 2019
 */
@Service
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private Set<Post> userPosts = new HashSet<>();



    public PostService(PostRepository postRepository,UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }




    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post addNewPost(Post post) {
        post.setCreatedBy("ADMIN");
        Optional<User> userEmail =  userRepository.findByEmail("admin@gmail.com"); // CHANGE IT IN PRODUCTION
        postRepository.save(post);
        userPosts.add(post);
        post.setUser(userEmail.get());
        userRepository.save(userEmail.get());
        return post;
    }




}
















