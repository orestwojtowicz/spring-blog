package com.blog.demo.loadData;

import com.blog.demo.repositories.UserRepository;
import com.blog.demo.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by damiass on Sep, 2019
 */
@Slf4j
@Component
public class LoadData implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();



    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder loginEncoder = new BCryptPasswordEncoder();
        String passForLogin = "{bcrypt}" + loginEncoder.encode("password");
        User user = new User("bigos@gmail.com", passForLogin);
        User admin = new User("admin@gmail.com", passForLogin);


        log.info("SAVING " + userRepository.save(user));
        userRepository.save(user);
        userRepository.save(admin);
        userRepository.findByEmail("bigos@gmail.com");
        log.info(userRepository.findByEmail("GETTING USER " + "bigos@gmail.com").toString());

    }
}
