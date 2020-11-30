package com.example.daodejing.dao;

import com.example.daodejing.model.ChapterText;

import java.util.List;

public interface ChapterTextDAO {

    List<ChapterText> returnAllChapters(int chapterNumber);
}
