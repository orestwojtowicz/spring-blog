package com.blog.demo.controller;

import com.blog.demo.entities.User;
import com.blog.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by damiass on Oct, 2019
 */
@Controller
public class Register {

    private UserService userService;

    public Register(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid User user, BindingResult bindingResult,
                                  Model model, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "register";
        } else {

        }
        // Register new user
        User newUser = userService.registerNewUser(user);
        redirectAttributes
               // .addAttribute("id",newUser.getId())
                .addAttribute("id", newUser.getPublicUserID())
                .addFlashAttribute("success",true);
        return "redirect:/register";

    }


}
