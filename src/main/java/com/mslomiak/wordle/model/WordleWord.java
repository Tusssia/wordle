package com.mslomiak.wordle.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@Table(name = "words")
@Entity
public class WordleWord {

    public WordleWord(String word, Date date) {
        this.Word = word;
        this.Date = date;
    }

    @Id
    @Column(name = "word_ID")
    private int Word_ID;

    @Column(name = "word")
    private String Word;

    @Column(name = "date")
    private Date Date;

}
