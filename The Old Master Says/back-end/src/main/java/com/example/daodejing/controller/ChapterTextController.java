package com.example.daodejing.controller;

import com.example.daodejing.dao.ChapterTextDAO;
import com.example.daodejing.model.ChapterText;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChapterTextController {

    private ChapterTextDAO chapterTextDAO;

    public ChapterTextController(ChapterTextDAO chapterTextDAO) {
        this.chapterTextDAO = chapterTextDAO;
    }

    @GetMapping("/translations/{chapterNumber}")
    public List<ChapterText> fetchTranslationsByChapter(@PathVariable int chapterNumber) {
        return chapterTextDAO.returnAllChapters(chapterNumber);
    }
}
