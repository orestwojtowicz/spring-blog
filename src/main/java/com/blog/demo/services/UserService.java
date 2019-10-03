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
        user.addRole(roleRepository.findByName("ROLE_USER"));
        String publicUserID = randomPublicUserID.generateRandomBytes();
        user.setPublicUserID(publicUserID);
        // change to true after activation
        user.setEnabled(false);
        user.setActivationCode(UUID.randomUUID().toString());

        emailSenderService.sendActivationEmail(user);

        userRepository.save(user);


        return user;
    }


    //{bcrypt}$2a$10$fhDQHaKvtNa.BPjaa5a1/eayTZgjI2id2CJ9L.1mnCwMSZ/a7P5M6

    public User setActivationCodeAndSendAndEmail(User user) {

        Optional<User> userEmail = userRepository.findByEmail(user.getEmail());
        log.info("FINDING USER EMAIL " );
        if (userEmail.isPresent())
            //userEmail.get().setActivationCode(UUID.randomUUID().toString());
        log.info("EMAIL FOUND " + userEmail.get().getEmail());
        emailSenderService.sendActivationEmail(user);


        User userrek = userEmail.get();
        userrek.setResetPasswordToken(UUID.randomUUID().toString());
        userrek.setEmail("user@gmail.com");
        userRepository.save(userrek);

        // userRepository.setUserById(user.getPassword(), "nowymajl@gmail.com");

       // userRepository.resetUserPassword(userEmail.get().getPassword());
        //userRepository.save(user);

        log.info("RETURNING USER SERVICE OBJECT USER ");
        return userrek;
    }



    public void sendActivationEmail(User user) {
        emailSenderService.sendActivationEmail(user);
    }

    public void sendWelcomeEmail(User user) {
        emailSenderService.sendWelcomeEmail(user);
    }


}
// MAIL CONFIRMATION
// https://www.codebyamir.com/blog/user-account-registration-with-spring-boot