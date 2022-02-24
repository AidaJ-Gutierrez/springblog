package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {


    // returns a view:
    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }


    //using models to pass data:
    @GetMapping("/roll-dice/{n}")
    public String diceGuess(@PathVariable int n, Model model) {
        Random randomNum = new Random();
        int random = randomNum.nextInt((6 - 1) + 1) + 1;

        model.addAttribute("diceRoll", random);
        model.addAttribute("userGuess", n);
        model.addAttribute("isCorrect", random == n);

        return "roll-dice";

    }


}
