package com.blog.demo.services;

import com.blog.demo.entities.User;
import com.blog.demo.repositories.RoleRepository;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.utills.RandomPublicUserID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by damiass on Oct, 2019
 */
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private final RandomPublicUserID randomPublicUserID;
    private final EmailSenderService emailSenderService;


    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       EmailSenderService emailSenderService)  {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        randomPublicUserID = new RandomPublicUserID();
        encoder = new BCryptPasswordEncoder();
        this.emailSenderService = emailSenderService;
    }


    public User registerNewUser(User user) {


        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setPassword(secret);
        user.setConfirmPassword(secret);
        user.addRole(roleRepository.findByName("ROLE_USER"));
        String publicUserID = randomPublicUserID.generateRandomBytes();
        user.setPublicUserID(publicUserID);
        // change to true after activation
        user.setEnabled(false);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setUserCommentCount(0);
       // user.setCreationDate(LocalDateTime.now());
       // user.setLastModifiedDate(LocalDateTime.now());
        emailSenderService.sendActivationEmail(user);

        userRepository.save(user);

       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
       // formatter.format(LocalDateTime.now());

        return user;
    }

    /*
    *  Method responsible for sending email message with reset password activation link
    * Secret Token is genereted and saved to database, also token is used in activation link
    * Token will be valid for 12 hours - See SpringSchedulingConfig.class for more details
    * */

    public User setActivationCodeAndSendAndEmail(User user) {

        log.info(user.getEmail() + " PRZED SPRAWDZENIEM");
// .equals(userRepository.findByEmail(user.getEmail())
        if (user.getEmail() != null) {
            Optional<User> userEmail = userRepository.findByEmail(user.getEmail());
            log.info(userEmail.get().getEmail() + " EMAIL ");
            User returnUser = userEmail.get();
            returnUser.setResetPasswordToken(UUID.randomUUID().toString()); // setting secret token
            emailSenderService.sendResetPasswordEmail(returnUser);
            String newPassword = "{bcrypt}" + encoder.encode(user.getPassword()); // saving new password to database
            returnUser.setPasswordForChange(newPassword);
            userRepository.save(returnUser);

            return returnUser;
        }

        throw new EntityNotFoundException();

    }


    public void sendActivationEmail(User user) {
        emailSenderService.sendActivationEmail(user);
    }

    public void sendWelcomeEmail(User user) {
        emailSenderService.sendWelcomeEmail(user);
    }

}




















