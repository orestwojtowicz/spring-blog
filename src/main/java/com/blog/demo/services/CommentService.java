package com.blog.demo.services;


import com.blog.demo.entities.Comment;
import com.blog.demo.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by damiass on Sep, 2019
 */
@Service
public class CommentService  {

    private CommentRepository commentRepository;

    CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findAllByPostId(Long id) {
        return commentRepository.findAllByPostId(id);
    }




}
