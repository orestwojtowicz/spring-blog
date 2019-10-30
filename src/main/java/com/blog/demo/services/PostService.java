package com.blog.demo.services;


import com.blog.demo.entities.Image;
import com.blog.demo.entities.Post;
import com.blog.demo.entities.User;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.services.dateFormatter.FormatDate;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.*;

/**
 * Created by damiass on Sep, 2019
 */
@Service
public class PostService extends FormatDate {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private Set<Post> userPosts = new HashSet<>();
    private ImageService imageService;



    public PostService(PostRepository postRepository,UserRepository userRepository, ImageService imageService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }




    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post addNewPost(Post post) {
        //post.setCreatedBy("ADMIN");
        Optional<User> userEmail =  userRepository.findByEmail("admin@gmail.com"); // CHANGE IT IN PRODUCTION
        postRepository.save(post);
        userPosts.add(post);
        post.setUser(userEmail.get());
        userRepository.save(userEmail.get());
        return post;
    }


    public int saveImageToPost(String name, MultipartFile file, Post post) {

        Post newPost = addNewPost(post);

        try {
            byte[] image = file.getBytes();
            Image model = new Image(name, image);
            int saveImage = imageService.saveImage(model);

            if (saveImage == 1)
            newPost.setImage(model);

            newPost.setMyDate(formatDateToDayMonthYear());

            byte[] encodingImage = Base64.getEncoder().encode(image);
            String saveEncodedString = new String(encodingImage, "UTF-8");
            newPost.getImage().setImageString(saveEncodedString);
            postRepository.save(newPost);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;

    }





}
















