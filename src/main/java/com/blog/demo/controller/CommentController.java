package com.blog.demo.controller;

import com.blog.demo.entities.Comment;
import com.blog.demo.entities.User;
import com.blog.demo.repositories.CommentRepository;
import com.blog.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by damiass on Oct, 2019
 */
@Controller
@Slf4j
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;


/*    @PostMapping("/add/comment")
    public String addCommentToPost(@Valid Comment comment, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggerUserName = auth.getName();
        Optional<User> userData = userRepository.findByEmail(loggerUserName);
        String nick = userData.get().getNick();

        comment.setUser(userData.get());
        commentRepository.save(comment);

        return "redirect:/";
    }*/








}
