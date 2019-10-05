package com.blog.demo.controller;

import com.blog.demo.entities.User;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    public String resetPassword() {
         return "reset-password";
    }


    // POST request for getting information about user, who wants to reset password and send activation email with secret token
    @PostMapping("/reset/password")
    public String resetPassword(User user, BindingResult bindingResult,
                                  Model model, RedirectAttributes redirectAttributes
    ) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            log.info("ERROR IN IF RESETPASSWORD");
            return "reset-password";
        } else {

        }


        User resetPasswordUser = userService.setActivationCodeAndSendAndEmail(user);

        redirectAttributes
                // .addAttribute("id",newUser.getId())
                .addAttribute("id", resetPasswordUser.getPublicUserID())
                .addFlashAttribute("success",true);
        log.info("WSZYSTKO OK CHCE ZWROCIC --- redirect:/reset-password");
        return "redirect:/reset-password";

    }

 /*     check if provided link and token is valid -> if sa reset user password
        example link
        http://localhost:8080/activate/uazek2@wp.pl/ca78e501-85ac-4e2e-b97f-514cfff759ca*/
//

 @GetMapping("/reset/password/{email}/{resetToken}")
    public String activateResetPassword(@PathVariable String email, @PathVariable String resetToken) {

        Optional<User> user = userRepository.findByEmailAndResetPasswordToken(email, resetToken);
        userRepository.updatePassword(user.get().getPasswordForChange(), user.get().getId());

    return "redirect:/";

 }

}
























