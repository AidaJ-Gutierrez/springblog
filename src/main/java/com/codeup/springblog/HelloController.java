package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // annotation for the whole class
public class HelloController {

// this annotation is specific to this method
// using path variables /{name}

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String hello(@PathVariable String name){
//        return "<h1>Hello from Spring!</h1>"; // adding HTML tags
        return "Hello, " + name + "!"; // concat using the path variables
    }

    @GetMapping("/test") // this annotation is specific to this method
    @ResponseBody
    public String test(){
        return "test";
    }


    //REQUEST MAPPING
    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String increment(@PathVariable int number){
        return number + " plus one is " + (number + 1) + "!";
    }
}
