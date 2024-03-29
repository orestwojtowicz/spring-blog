package com.blog.demo.controller;


import com.blog.demo.entities.Image;
import com.blog.demo.entities.Post;
import com.blog.demo.repositories.ImageRepository;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.services.ImageService;
import com.blog.demo.services.PostService;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by damiass on Sep, 2019
 */
@Controller
@Slf4j
public class HomeController {

    private final PostService postService;
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    @Autowired
    PostRepository postRepository;



    public HomeController(PostService postService, PostRepository postRepository,
                          ImageService imageService, ImageRepository imageRepository) {
        this.postService = postService;
        this.imageService = imageService;
        this.imageRepository = imageRepository;

    }


//https://stackoverflow.com/questions/48235379/how-to-display-byte-array-from-a-model-in-thymeleaf?rq=1
     @GetMapping("image/{id}")
    public void getImageDetails(@PathVariable Long id, HttpServletResponse response) throws IOException {

        response.setContentType("image/jpeg");
         Image image = imageService.getImage(id);
        InputStream is = new ByteArrayInputStream(image.getImage());
        IOUtils.copy(is, response.getOutputStream());
        //IOUtils(is, response.getOutputStream());
    }



    @GetMapping("/topics/{topicName}")
    public String getTopicContent(@PathVariable String topicName, Model model) {
        List<Post> posts =  postRepository.findAllByPostTopics(topicName);

        String postImage = "";

        for (Post post : posts) {
            postImage = post.getImage().getImageString();
        }

        log.info("OBRAZEK " + postImage);

         model.addAttribute("topics", posts);
         model.addAttribute("image", postImage);

         return "/post/topics";
    }



    @GetMapping("/")
    public String mainPage(Model model) {
           model.addAttribute("posts", postService.findAll());

          var allPosts = postService.findAll();
          List<String> notSortedTopics = new ArrayList<>();
          for (Post post : allPosts) {
              notSortedTopics.add(post.getPostTopics());
          }

          List<String> sortedTopics = notSortedTopics.stream().distinct().collect(Collectors.toList());
          for (String sortPost : sortedTopics) {
              postRepository.findDistinctByPostTopics(sortPost);
          }

            int size = notSortedTopics.size() - sortedTopics.size();
            model.addAttribute("sorted", sortedTopics);
            model.addAttribute("size", size);

          return "main";
    }



    @GetMapping("/login")
    public String login () {
        return "login";
    }
}


