package com.blog.demo.services;

import com.blog.demo.entities.User;
import com.blog.demo.repositories.RoleRepository;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.utills.RandomPublicUserID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository)  {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        randomPublicUserID = new RandomPublicUserID();
        encoder = new BCryptPasswordEncoder();
    }

    public User registerNewUser(User user) {

        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setPassword(secret);
        user.addRole(roleRepository.findByName("ROLE_USER"));
        String publicUserID = randomPublicUserID.generateRandomBytes();
        user.setPublicUserID(publicUserID);
        // change it later to false
        user.setEnabled(true);
        log.info("SAVING USER " + user.toString());
        userRepository.save(user);
        log.info("User saved successfully " + user.toString());


        return user;
    }


    // USER PROFILE INFORMATION
    // user email = auth.getName()

  /*  public String getUserProfileInformation(String userEmail) {

        Optional<User> userData = userRepository.findByEmail(userEmail);

        String nick = userData.get().getNick();



    }*/


}
// MAIL CONFIRMATION
// https://www.codebyamir.com/blog/user-account-registration-with-spring-boot