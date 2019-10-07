package com.blog.demo.controller;

import com.blog.demo.entities.Image;
import com.blog.demo.entities.Post;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.services.ImageService;
import com.blog.demo.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Base64;

/**
 * Created by damiass on Oct, 2019
 */
@Slf4j
@Controller
public class PostController {

    PostService postService;
    private PostRepository postRepository;
    @Autowired
    private ImageService imageService;

    public PostController(PostService postService,PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
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

    // upload images method
    @PostMapping("/fileupload")
    public String fileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        try {

            byte[] image = file.getBytes();
            Image model = new Image(name, image);
            int saveImage = imageService.saveImage(model);
            if (saveImage == 1) {
                return "success";
            } else {
                return "error";
            }
        } catch (Exception e) {
            log.info("BLAD W KONTROLERZE " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/getDetail/{id}")
    public String getDbDetils(@PathVariable String id, Model model) {
        try {

            Image imagesObj = imageService.getImages(Long.parseLong(id));
            model.addAttribute("id", imagesObj.getId());
            model.addAttribute("name", imagesObj.getName());
            byte[] encode = Base64.getEncoder().encode(imagesObj.getImage());
            model.addAttribute("image", new String(encode, "UTF-8"));
            log.info("ZWRACAM IMAGEDETAILS .....................");
            return "imagedetails";
        } catch (Exception e) {

            model.addAttribute("message", "Error in getting image");
            return "redirect:/";
        }
    }


}





















