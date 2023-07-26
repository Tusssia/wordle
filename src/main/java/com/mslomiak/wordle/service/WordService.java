package com.mslomiak.wordle.service;

import com.mslomiak.wordle.model.WordleWord;
import com.mslomiak.wordle.repository.WordleWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WordService {

    @Autowired
    private WordleWordRepository wordleWordRepository;

    public void createWordOfDay() {
        if(!wordOfDayCreated()) {
            List<String> words = null;

            String wordFile = "/Users/mslomiak/wordle/src/main/resources/static/list_of_words.txt";
            try (Stream<String> lines = Files.lines(Paths.get(wordFile))){
                words = lines.collect(Collectors.toList());

            } catch (IOException e) {
                System.out.println("File cannot be read");
            }

            if(!words.isEmpty()) {
                int randomNumber = ThreadLocalRandom.current().nextInt(0, words.size()-1);
                wordleWordRepository.save(new WordleWord(words.get(randomNumber), new Date()));
            }
        }
    }

    public String getWordOfTheDay() {
        Date today = new Date();
        WordleWord wordOfDay = getWordByDate(today);
        if(wordOfDay == null) {
            return "No word of the day exists";
        } else {
            return wordOfDay.getWord();
        }
    }

    public boolean checkWord(String word) {
        String wordOfTheDay = getWordOfTheDay();
        return word.equals(wordOfTheDay) ? true : false;
    }

    private boolean wordOfDayCreated() {
        Date today = new Date();
        return getWordByDate(today) == null ? false : true;
    }

    private WordleWord getWordByDate(Date date) {
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        return wordleWordRepository.findWordByDate(dateSql);
    }

}
