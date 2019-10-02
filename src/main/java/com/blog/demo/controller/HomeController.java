package com.blog.demo.controller;

import com.blog.demo.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by damiass on Sep, 2019
 */
@Controller
public class HomeController {



    private final PostService postService;

    public HomeController(PostService postService) {
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
