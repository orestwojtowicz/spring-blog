package com.blog.demo.services;


import com.blog.demo.repositories.CommentRepository;
import org.springframework.stereotype.Service;

/**
 * Created by damiass on Sep, 2019
 */
@Service
public class CommentService {

    private CommentRepository commentRepository;

    CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

}
