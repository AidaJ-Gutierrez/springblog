package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PostController {


    private final PostRepository postDao;
    private final UserRepository userDao;//injected to the controller

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;//injected to the controller
    }


    @GetMapping(path = "/posts")
    public  String postIndex(Model model){
        List<Post> allThePosts = postDao.findAll();
        model.addAttribute("allThePosts", allThePosts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model){
       Post post = postDao.getById(id);
       model.addAttribute("postID", id);
       model.addAttribute("newPost", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")

    public String createPostForm(Model model){
        model.addAttribute("newPost", new Post());
        return "post/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        post.setUser(userDao.getById(1L));
        postDao.save(post);
        return "redirect:/posts";
    }
    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        Post posttoEdit = postDao.getById(id);
        model.addAttribute("postToEdit", posttoEdit.getId());
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String submitEdit(@PathVariable long id, @ModelAttribute Post post) {
        Post postToEdit = postDao.getById(id);
        postToEdit.setTitle(post.getTitle());
        postToEdit.setBody(post.getBody());
        postDao.save(postToEdit);

        return "redirect:/posts";
    }


    @GetMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }


}
