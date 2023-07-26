package com.mslomiak.wordle.repository;

import com.mslomiak.wordle.model.WordleWord;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;


@Repository
public interface WordleWordRepository extends JpaRepository<WordleWord, Integer> {

    @Query("SELECT w FROM WordleWord w WHERE w.Date = :date")
    WordleWord findWordByDate(@Param("date") Date date);
}
