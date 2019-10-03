package com.blog.demo.controller;

import com.blog.demo.entities.User;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by damiass on Oct, 2019
 */
@Controller
@Slf4j
public class ResetPasswordController {


    private UserRepository userRepository;
    private UserService userService;
    public ResetPasswordController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/reset/password")
    public String resetPassword(User user, Model model) {


                    log.info("PUSTY MAPPING  /reset/password !!");
        return "reset-password";
    }


    // PLACE FOR CHANGE NEW PASSWORD
    @PostMapping("/reset/password")
    public String resetPassword(User user, BindingResult bindingResult,
                                  Model model, RedirectAttributes redirectAttributes
    ) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        model.addAttribute("user", existingUser.get());
        user.setNick(existingUser.get().getNick());
        log.info("GETTING USER " + existingUser.get().getEmail()); // OK
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            log.info("ERROR IN IF RESETPASSWORD");
            return "reset-password";
        } else {

        }
        // Service for RESETING PASSWORD
            log.info("PRZED   user = userService.setActivationCodeAndSendAndEmail(user);");
        user = userService.setActivationCodeAndSendAndEmail(user);
        log.info("PO   user = userService.setActivationCodeAndSendAndEmail(user);");
        redirectAttributes
                // .addAttribute("id",newUser.getId())
                .addAttribute("id", user.getPublicUserID())
                .addFlashAttribute("success",true);
        log.info("WSZYSTKO OK CHCE ZWROCIC --- redirect:/reset/password");
        return "redirect:/reset-password";

    }

}
























