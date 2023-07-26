package com.mslomiak.wordle.controller;

import com.mslomiak.wordle.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private WordService wordService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to wordle";
    }

    @GetMapping("/game")
    public String newGame() {
        wordService.createWordOfDay();
        return "You started a new game";
    }

    @GetMapping("/wordOfTheDay")
    public String wordOfTheDay() {
        return wordService.getWordOfTheDay();
    }

    @GetMapping("/newgame")
    public String guessWord (@RequestParam String word) {
        if (wordService.checkWord(word)) {
            return "you won!";
        } else {
            return "try again";
        }
    }

}
