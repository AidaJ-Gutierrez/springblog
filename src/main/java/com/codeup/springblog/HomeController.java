package com.codeup.springblog;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @GetMapping("/")
//    @ResponseBody - removed to use views
    public String landingPage(){
        return  "welcome";
    }

    //Models
    @GetMapping("/quote-of-the-day//by/{author}")
    public String quote(@PathVariable String author, Model model){
        model.addAttribute("author", author);
        return "quotes";
    }

    //Exampl of receiving and Sending data

    @GetMapping("/join")
    public String showJoinForm(){
        return "join";
    }
    @PostMapping("/join")
    public String joinCohort(@RequestParam(name="cohort") String cohort, Model model){
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "join";
    }


}
