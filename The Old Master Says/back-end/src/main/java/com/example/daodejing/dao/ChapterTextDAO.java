package com.example.daodejing.dao;

import com.example.daodejing.model.ChapterText;
import com.example.daodejing.model.Quote;

import java.util.List;

public interface ChapterTextDAO {

    List<ChapterText> returnAllChapters(int chapterNumber);

    List<ChapterText> returnAllTranslations();

    List<Quote> returnAllMatching(String searchText);

    int returnWordCount(String searchWord);
}
