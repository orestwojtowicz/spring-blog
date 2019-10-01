package com.blog.demo.configuration.security;


import com.blog.demo.UserRepository;
import com.blog.demo.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

   // UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException;


    private UserRepository userRepository;

    UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            log.info("EMPTY USER  ");
            throw new UsernameNotFoundException(email);
        }
        log.info("RETURNING " + user.get());
        return user.get();
    }
}
