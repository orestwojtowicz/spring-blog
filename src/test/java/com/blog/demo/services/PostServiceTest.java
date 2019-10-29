package com.blog.demo.services;

import com.blog.demo.entities.Post;
import com.blog.demo.entities.User;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * Created by damiass on Oct, 2019
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private PostService postService;

    private List<Post> posts;
    private Post first;
    private Post second;
    private User adminUser;

    @BeforeEach
    void setUp() {
        first = new Post("Content", "title");
        second = new Post("Second Content", "title2");
        posts = new ArrayList<>();
        posts.add(first);
        posts.add(second);

        adminUser = new User();
        adminUser.setEmail("admin@gmail.com");
        first.setCreatedBy("ADMIN");
        first.setId(1L);

    }

    @Test
    void findAll() {
        given(postRepository.findAll()).willReturn(posts);

        // when
        List<Post> findPost = postService.findAll();
        // then
        then(postRepository).should(times(1)).findAll();
        assertThat(findPost).hasSize(2);
    }

    @Test
    @DisplayName("Add new post")
    void addNewPost() {

        // given
        Optional<User> findByEmail = userRepository.findByEmail(adminUser.getEmail());
        given(postRepository.save(first)).willReturn(first);
        given(userRepository.findByEmail(adminUser.getEmail()))
                .willReturn(findByEmail);

        final String MATCH_USER_EMAIL = "admin@gmail.com";

        given(postRepository.save(argThat(argument -> argument.getUser().getEmail()
                .equals(MATCH_USER_EMAIL)))).willReturn(first);

        given(userRepository.findByEmail("random@gmail.com")).willThrow(new RuntimeException("Email not found"));

        assertThat(first.getCreatedBy().equals("ADMIN"));
        then(postRepository).shouldHaveNoMoreInteractions();


    }
}
















