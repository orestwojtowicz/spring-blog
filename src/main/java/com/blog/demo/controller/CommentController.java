package com.blog.demo.controller;

import com.blog.demo.entities.Comment;
import com.blog.demo.repositories.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by damiass on Oct, 2019
 */
@Controller
@Slf4j
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;


    @PostMapping("/add/comment")
    public String addCommentToPost(@Valid Comment comment, Model model) {

        commentRepository.save(comment);


        return "redirect:/";
    }








}
