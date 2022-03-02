package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;


    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String post(Model model) {
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("allPosts", allPosts);


        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postId(@PathVariable long id, Model model) {

        Post onePost = postDao.findPostById(id);
        model.addAttribute("onePost", onePost);
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreate(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String postCreate(@ModelAttribute Post post) {
//        User user = userDao.getById(1L);
        post.setUser(userDao.getById(1L));
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(loggedInUser);
        emailService.prepareAndSendPost(post,"Testing", "Did this work");
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEdit(@PathVariable long id, Model model) {
        Post editPost = postDao.getById(id);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(editPost.getUser().getId() == loggedInUser.getId()){
            model.addAttribute("postToEdit", editPost);
            return "/posts/edit";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/posts/{id}/edit")
    public String postEdit(@PathVariable long id, @ModelAttribute Post post) {
        Post postEdit = postDao.getById(id);
        postEdit.setTitle(post.getTitle());
        postEdit.setBody(post.getBody());
        postDao.save(postEdit);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/delete")
    public String postDelete(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }



}