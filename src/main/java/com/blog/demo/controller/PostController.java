package com.blog.demo.controller;

import com.blog.demo.entities.Comment;
import com.blog.demo.entities.Image;
import com.blog.demo.entities.Post;
import com.blog.demo.entities.User;
import com.blog.demo.repositories.CommentRepository;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.services.CommentService;
import com.blog.demo.services.ImageService;
import com.blog.demo.services.PostService;

import com.blog.demo.services.dateFormatter.FormatDate;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;
import org.apache.commons.text.StringEscapeUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by damiass on Oct, 2019
 */
@Slf4j
@Controller
public class PostController extends FormatDate {

    private final PostService postService;
    private final PostRepository postRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private  Set<Comment> comments = new HashSet<>();


    public PostController(PostService postService,PostRepository postRepository,
                           CommentService commentService,   CommentRepository commentRepository,
                           UserRepository userRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
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


    // GET SINGLE POST FIX THIS znalezc uzytkownika po napisanym komentarzy, czy cos podobnego
    // i wtedy sciagnac jego avatar
    @GetMapping("/post/{id}")
    public String getSinglePost(Model model, @PathVariable Long id) {


        Optional<Post> getPost = postRepository.findById(id);
        log.info("OPTIONAL " + getPost.get());
        String postContent = getPost.get().getPostContent();
        String postTitle = getPost.get().getPostTitle();
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("postTitle", postTitle);
        model.addAttribute("postContent", postContent);
        model.addAttribute("comment", new Comment());
        model.addAttribute("allComments", commentService.findAllByPostId(id));
        model.addAttribute("commentSize", commentService.findAllByPostId(id).size());


        return "readpost";
    }

    @RequestMapping(value = "/post/delete/{id}")
    private String deleteComment(@PathVariable(name = "id") Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        commentRepository.deleteById(comment.get().getId());

         return "redirect:/";
}

    @PostMapping("/post/{id}")
    public String addCommentToPost(@Valid Comment comment, Model model, @PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggerUserName = auth.getName();
        Optional<User> userData = userRepository.findByEmail(loggerUserName);
        String nick = userData.get().getNick();


        Optional<Post> post = postRepository.findById(id);

        comment.setCommentDate(formatDateToDayMonthYearHours());
        model.addAttribute("id", id);
        model.addAttribute("comment", new Comment());
        int userCommentCount = userData.get().getUserCommentCount();
        userCommentCount = userCommentCount + 1;
        userRepository.updateUserCommentcount(userCommentCount, userData.get().getId());
        int value = post.get().getPostCommentsSize();
        value = value + 1;
        post.get().setPostCommentsSize(value);
        postRepository.updatePostCommentsSize(value, id);
        comments.add(comment);
        comment.setPost(post.get());
        comment.setUser(userData.get());
        commentRepository.save(comment);
         return "redirect:/";
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





















