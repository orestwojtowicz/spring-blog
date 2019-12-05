package com.blog.demo.services;


import com.blog.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Created by damiass on Sep, 2019
 */
@Service
public class UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
