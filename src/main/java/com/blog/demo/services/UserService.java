package com.blog.demo.services;

import com.blog.demo.entities.User;
import com.blog.demo.repositories.RoleRepository;
import com.blog.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by damiass on Oct, 2019
 */
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        encoder = new BCryptPasswordEncoder();
    }

    public User registerNewUser(User user) {

        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setPassword(secret);
        user.addRole(roleRepository.findByName("ROLE_USER"));
        // change it later to false
        user.setEnabled(true);
        log.info("SAVING USER " + user.toString());
        userRepository.save(user);
        log.info("User saved successfully " + user.toString());


        return user;
    }







}
