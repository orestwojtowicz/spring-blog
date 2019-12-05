package com.blog.demo.controller;

import com.blog.demo.entities.Image;
import com.blog.demo.entities.Post;
import com.blog.demo.entities.User;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.services.ImageService;
import com.blog.demo.services.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sound.midi.SoundbankResource;
import java.util.Base64;
import java.util.Optional;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Mock
            Post post;

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
    void loadPostPage() throws Exception {
        mockMvc.perform(get("/post.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("post"))
                .andExpect(view().name("post"));
    }

    @Test
    @DisplayName("Error while creating post")
    void addNewPostError() throws Exception {
        mockMvc.perform(post("/post.html")
                .param("postContent", "content"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("post"))
                .andExpect(model().attributeHasFieldErrors("post", "postTitle"))
                .andExpect(view().name("post"));
    }

    @Test
    @DisplayName("Post added, postService.addNewPost is tested in PostServiceTest.class")
    void addNewPostOk() throws Exception {

       }


    @Test
    void getSinglePost() throws Exception {


    }

    @Test
    void fileUpload() {
    }

    @Test
    void getImageDetails() throws Exception {
        Image image = new Image();
        byte[] byteImg = new byte[30];

        image.setId(1L);
        image.setName("image");
        image.setImage(byteImg);

        given(imageService.getImage(anyLong())).willReturn(image);
        byte[] encode = Base64.getEncoder().encode(image.getImage());
        mockMvc.perform(get("/image/{id}",1))
                .andExpect(model().attribute("name", image.getName()))
                .andExpect(model().attribute("image", new String(encode, "UTF-8")))
                .andExpect(view().name("imagedetails"));

    }
    @Test
    void getImageDetailsError() throws Exception {
        Image image = new Image();
        image.setId(1L);
        image.setName("image");


        Assertions.assertThrows(NullPointerException.class, ()->
                Base64.getEncoder().encode(image.getImage()));

    }

}























