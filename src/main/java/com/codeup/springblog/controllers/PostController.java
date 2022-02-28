package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {


    private PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }


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

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm(Model model){
        model.addAttribute("post", new Post());
        return "post/create";
    }

    public String submitCreateForm(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
        Post newPost = new Post(title, body);
        postDao.save(newPost);

        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        Post posttoEdit = postDao.getById(id);
        model.addAttribute("postToEdit", posttoEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String submitEdit(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @PathVariable long id) {
        Post postToEdit = postDao.getById(id);
        postToEdit.setTitle(title);
        postToEdit.setBody(body);
        postDao.save(postToEdit);
        return "redirect:/posts";
    }


    @GetMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }


}
