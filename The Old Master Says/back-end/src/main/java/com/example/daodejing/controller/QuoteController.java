package com.example.daodejing.controller;

import com.example.daodejing.dao.QuoteDAO;
import com.example.daodejing.model.Quote;
import com.example.daodejing.model.Translation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuoteController {
    private QuoteDAO quoteDAO;

    public QuoteController(QuoteDAO quoteDAO) {
        this.quoteDAO = quoteDAO;
    }

    @GetMapping("/quote")
    public List<Quote> listQuotes (@RequestParam String searchText) {
        return quoteDAO.returnAllMatching(searchText);
    }

    @GetMapping("/translations")
    public List<Translation> listTranslations () {
        return quoteDAO.returnAllTranslations();
    }
}
