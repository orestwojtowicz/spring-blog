package com.blog.demo.controller;

import com.blog.demo.repositories.PostRepository;
import com.blog.demo.services.ImageService;
import com.blog.demo.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by damiass on Oct, 2019
 */
@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;
    @Mock
    private PostRepository postRepository;
    @Mock
    ImageService imageService;
    @InjectMocks
    PostController controller;

    MockMvc mockMvc;



    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void loadPostToRead() throws Exception {
        mockMvc.perform(get("/read/post.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("readpost"));
    }

    @Test
    void loadPostPage() {

    }

    @Test
    void addNewPost() {
    }

    @Test
    void getSinglePost() {
    }

    @Test
    void fileUpload() {
    }

    @Test
    void getImageDetails() {
    }
}