package com.blog.demo.controller;

import com.blog.demo.entities.User;
import com.blog.demo.repositories.CommentRepository;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

/**
 * Created by damiass on Oct, 2019
 */
@Slf4j
@Controller
public class ProfileController {

    private UserRepository userRepository;
    private CommentRepository commentRepository;

    public ProfileController(UserRepository userRepository,CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Secured("ROLE_ADMIN, ROLE_USER")
    @GetMapping("/profile")
    public String getUserName(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = auth.getName();
        Optional<User> userData = userRepository.findByEmail(loggedUserName);
        String nick = userData.get().getNick();
        model.addAttribute("userName", loggedUserName);
        model.addAttribute("nick", nick);
        return "profile";
    }
}
















