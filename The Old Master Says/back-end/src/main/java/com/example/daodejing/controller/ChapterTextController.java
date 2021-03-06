package com.example.daodejing.controller;

import com.example.daodejing.dao.ChapterTextDAO;
import com.example.daodejing.model.ChapterText;
import com.example.daodejing.model.Quote;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ChapterTextController {

    private ChapterTextDAO chapterTextDAO;

    public ChapterTextController(ChapterTextDAO chapterTextDAO) {
        this.chapterTextDAO = chapterTextDAO;
    }

    @GetMapping("/translations")
    public List<ChapterText> fetchTranslationsByChapter(@RequestParam int chapterNumber) {
        return chapterTextDAO.returnAllChapters(chapterNumber);
    }

    @GetMapping("/quote")
    public List<Quote> listQuotes (@RequestParam String searchText) {
        return chapterTextDAO.returnAllMatching(searchText);
    }

    @GetMapping("/authors")
    public List<ChapterText> listTranslations () {
        return chapterTextDAO.returnAllTranslations();
    }

    @GetMapping("/word")
    public int wordCount(@RequestParam String searchWord) {
        return chapterTextDAO.returnWordCount(searchWord);
    }
}
