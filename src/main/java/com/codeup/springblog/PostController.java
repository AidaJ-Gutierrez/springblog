package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    @GetMapping(path = "/posts")
    @ResponseBody
//    public String postsIndex(){return "Posts index page";}
    public  String postIndex(Model model){
        List<Post> allThePosts = new ArrayList<>();
        allThePosts.add(new Post("This is Another Post!"));
        model.addAttribute("post", allThePosts);
        return "post/index";
    }



    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String individualPost(@PathVariable long id){
        return "View an individual post";
    }

    @GetMapping("/posts/create")
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
