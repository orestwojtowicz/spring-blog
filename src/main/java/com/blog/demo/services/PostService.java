package com.blog.demo.services;


import com.blog.demo.repositories.PostRepository;
import org.springframework.stereotype.Service;

/**
 * Created by damiass on Sep, 2019
 */
@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

}
