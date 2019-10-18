package com.blog.demo.services;

import com.blog.demo.entities.Post;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


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

    @BeforeEach
    void setUp() {
        first = new Post("Content");
        second = new Post("Second Content");
        posts = new ArrayList<>();
        posts.add(first);
        posts.add(second);

    }

    @Test
    void findAll() {
        given(postRepository.findAll()).willReturn(posts);
        System.out.println(postRepository.findAll().toString());
        // when
        List<Post> findPost = postService.findAll();
        // then
        then(postRepository).should(times(2)).findAll();
        assertThat(findPost).hasSize(2);
    }

    @Test
    void addNewPost() {
    }
}
















