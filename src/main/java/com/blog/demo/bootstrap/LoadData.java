package com.blog.demo.bootstrap;

import com.blog.demo.entities.Role;
import com.blog.demo.repositories.RoleRepository;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by damiass on Sep, 2019
 */
@Slf4j
@Component
public class LoadData implements CommandLineRunner {



    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private Role newUserRole = new Role("ROLE_USER");
    private Role adminRole = new Role("ROLE_ADMIN");



    public LoadData(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }


    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder loginEncoder = new BCryptPasswordEncoder();

        String passForLogin = "{bcrypt}" + loginEncoder.encode("password");
        User user = new User("bigos@gmail.com", passForLogin, "nick");
        User admin = new User("admin@gmail.com", passForLogin, "nick2");
        roleRepository.save(adminRole);
        roleRepository.save(newUserRole);
        user.addRole(adminRole);
        admin.addRole(newUserRole);


        log.info("SAVING " + userRepository.save(user));
        userRepository.save(user);
        userRepository.save(admin);
        userRepository.findByEmail("bigos@gmail.com");
        log.info(userRepository.findByEmail("GETTING USER " + "bigos@gmail.com").toString());

    }

}
