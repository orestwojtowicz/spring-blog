package com.blog.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by damiass on Sep, 2019
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }



}
