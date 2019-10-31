package com.blog.demo.controller;

import com.blog.demo.entities.Comment;
import com.blog.demo.entities.Image;
import com.blog.demo.entities.Post;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.services.CommentService;
import com.blog.demo.services.ImageService;
import com.blog.demo.services.PostService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;
import org.apache.commons.text.StringEscapeUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

/**
 * Created by damiass on Oct, 2019
 */
@Slf4j
@Controller
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final ImageService imageService;
    private final CommentService commentService;


    public PostController(PostService postService,PostRepository postRepository,
                          ImageService imageService, CommentService commentService) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.imageService = imageService;
        this.commentService = commentService;
    }

    @GetMapping("/read/post")
    public String loadPostToRead(Model model) {

        model.addAttribute("comment", new Comment());

        return "readpost";
    }


    @GetMapping("/post")
    public String loadPostPage(Model model) {
        model.addAttribute("post", new Post());

        return "post";
    }


    // GET SINGLE POST
    @GetMapping("/post/{id}")
    public String getSinglePost(Model model, @PathVariable Long id) {
        Optional<Post> getPost = postRepository.findById(id);
        String postContent = getPost.get().getPostContent();
        String postTitle = getPost.get().getPostTitle();
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("postTitle", postTitle);
        model.addAttribute("postContent", postContent);

        model.addAttribute("comment", new Comment());

        model.addAttribute("allComments", commentService.findAll());

        return "readpost";
    }


    // upload whole post content, postService.saveImageToPost return 1, if post added
    @PostMapping("/fileupload")
    public String fileUpload(@RequestParam("name") String name,
                             @RequestParam("file") MultipartFile file,
                             @Valid Post post) {

        if (postService.saveImageToPost(name, file, post) == 1) {
            log.info("Image uploaded successfully");
            return "post/success";
        }
        return "error";

    }


}





















