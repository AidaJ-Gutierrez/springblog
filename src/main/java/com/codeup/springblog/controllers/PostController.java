package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    @GetMapping(path = "/posts")
//    @ResponseBody
//    public String postsIndex(){return "Posts index page";}
    public  String postIndex(Model model){
        List<Post> allThePosts = new ArrayList<>();
        allThePosts.add(new Post(1,"This is one Post!", "This is the body"));
        allThePosts.add(new Post(2, "This is one more!", "This is one more body"));
        model.addAttribute("posts", allThePosts);
        return "posts/index";
    }
//    @GetMapping("/post/show")
//    public String rollDice(){
//        return "posts/show";
//    }


    @GetMapping("/posts/{id}")
//    @ResponseBody
    public String individualPost(@PathVariable long id, Model model){
       Post post = new Post(1,"New Post", "New Body");
       model.addAttribute("postID", id);
       model.addAttribute("newPost", post);
        return "posts/show";
    }

    @GetMapping("/post/create")
    @ResponseBody
    public String createPostForm(Model model){
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "Create a new post";
    }




}