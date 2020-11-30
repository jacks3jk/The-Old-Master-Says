package com.example.daodejing.dao;

import com.example.daodejing.model.Quote;
import com.example.daodejing.model.Translation;

import java.util.List;

public interface QuoteDAO {

    List<Quote> returnAllMatching(String searchText);

    List<Translation> returnAllTranslations();

}