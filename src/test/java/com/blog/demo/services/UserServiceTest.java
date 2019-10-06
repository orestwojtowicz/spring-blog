package com.blog.demo.services;

import com.blog.demo.controller.RegisterController;
import com.blog.demo.utills.RandomPublicUserID;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by damiass on Oct, 2019
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

    @Autowired
    private MockMvc mockMvc;

    RandomPublicUserID randomPublicUserID = new RandomPublicUserID();


    @Test
    void submitRegistrationFormSuccess() throws Exception {

        this.mockMvc
                .perform(
                        post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "test@gmail.com")
                        .param("nick", "kaktus")
                        .param("password", "password")
                ).andExpect(status().is3xxRedirection());
    }
}