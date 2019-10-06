package com.blog.demo.repositories;

import com.blog.demo.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

/**
 * Created by damiass on Oct, 2019
 */
class UserRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    private UserRepository userRepository;
    private String newPassword = "newPassword";

    @BeforeEach
    void setUp() {


    }

    @Test
    void findByEmailAndActivationCode() {
    }

    @Test
    void findByEmailAndResetPasswordToken() {
    }

    @Test
    void updatePassword() {

        User user = new User();
        user.setEmail("email@gmail.com");
        user.setActivationCode("123DSAdsa-3312DSAD21");
        user.setResetPasswordToken("12323-44SAD123-dsf#@3432");
        user.setPassword("password");

        assertAll("Test Properties Settings",
                () -> assertEquals(user.getEmail(), "email@gmail.com")
                // ...............
                );


        userRepository.save(user);
        Optional<User> userr = userRepository.findByEmail("email@gmail.com");
        assertEquals("email@gmail.com", userr.get().getEmail());
    }
}


/*
    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") Long id);*/
