package com.mslomiak.wordle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    public String wordle;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to wordle";
    }

    @GetMapping("/game")
    public String newGame() {
        wordle = "shrine";
        return "You started a new game";
    }

    @GetMapping("/game/")
    public String checkWord(@RequestParam String word) {
        if (wordle.equals(word)) {
            return "Great! You won";
        } else {
            return "Try again";
        }
    }

}
