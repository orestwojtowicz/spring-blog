package com.blog.demo.controller;

import com.blog.demo.entities.Post;
import com.blog.demo.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by damiass on Oct, 2019
 */
@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Mock
    private PostService postService;

    @Mock
    Model model;

    @InjectMocks
    HomeController controller;

    MockMvc mockMvc;

    List<Post> postList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        postList.add(new Post());
    }


    @Test
    void findAllPosts() {

        // when
        String postModel = controller.mainPage(model);
        // then
        then(postService).should().findAll();
        then(model).should().addAttribute("posts", postService.findAll());

       assertThat(postModel).isNotEmpty();
       assertThat(postModel).isEqualTo("main");
       assertThat(postList.size()).isNotNull();

    }

    @Test
    void mainPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("posts", postService.findAll()))
                .andExpect(view().name("main"));
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(get("/login.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}


















