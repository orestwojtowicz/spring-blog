package com.blog.demo.controller;

import com.blog.demo.entities.Post;
import com.blog.demo.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by damiass on Oct, 2019
 */
@Controller
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/read/post")
    public String loadPostToRead() {
        return "readpost";
    }


    @GetMapping("/post")
    public String loadPostPage(Model model) {
        model.addAttribute("post", new Post());
        return "post";
    }

    @PostMapping("/post")
    public String addNewPost(@Valid Post post, BindingResult bindingResult,
                             Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "post";
        } else {

        }
        Post newPost = postService.addNewPost(post);
        redirectAttributes.addAttribute("id", newPost.getId())
                .addFlashAttribute("success", true);
        return "redirect:/post";
    }


}
