package com.blog.demo.controller;


import com.blog.demo.repositories.PostRepository;
import com.blog.demo.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Created by damiass on Sep, 2019
 */
@Controller
@Slf4j
public class HomeController {



    private final PostService postService;


    public HomeController(PostService postService, PostRepository postRepository) {
        this.postService = postService;

    }


    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
