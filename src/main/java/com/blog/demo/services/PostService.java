package com.blog.demo.services;


import com.blog.demo.entities.Post;
import com.blog.demo.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by damiass on Sep, 2019
 */
@Service
public class PostService {

    private PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public List<Post> findAll() {
        return postRepository.findAll();
    }


}
