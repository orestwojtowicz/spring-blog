package com.blog.demo.controller;

import com.blog.demo.entities.User;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by damiass on Oct, 2019
 */
@Controller
@Slf4j
public class RegisterController {

    private UserService userService;
    private UserRepository userRepository;

    public RegisterController(UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
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

    // Z PALUCHA
    @GetMapping("/activate/{email}/{activationCode}")
    public String activate(@PathVariable String email, @PathVariable String activationCode) {
        Optional<User> user = userRepository.findByEmailAndActivationCode(email,activationCode);
        if( user.isPresent() ) {
            User newUser = user.get();
            newUser.setEnabled(true);
           // newUser.setConfirmPassword(newUser.getPassword());
            userRepository.save(newUser);
            userService.sendWelcomeEmail(newUser);
            return "auth/activated";
        }
        return "redirect:/";
    }

   // http://localhost:8080/activate/uazek2@wp.pl/ca78e501-85ac-4e2e-b97f-514cfff759ca

}



// jak gosc wejdzie w linka to tam mam ustawic nowe haslo!!!


























