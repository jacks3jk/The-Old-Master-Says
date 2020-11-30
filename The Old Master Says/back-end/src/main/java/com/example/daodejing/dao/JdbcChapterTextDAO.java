package com.example.daodejing.dao;

import com.example.daodejing.model.ChapterText;
import com.example.daodejing.model.Translation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcChapterTextDAO implements ChapterTextDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcChapterTextDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<ChapterText> returnAllChapters(int chapterNumber) {
        List<ChapterText> result = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT ct.chapter, ct.chapter_text, t.author, t.translation_id " +
                "FROM chapter_text AS ct " +
                "JOIN full_text AS ft ON ct.text_id = ft.text_id " +
                "JOIN translation AS t ON ft.translation_id = t.translation_id " +
                "WHERE ct.chapter = ?;", chapterNumber);
        while (rowSet.next()) {
            ChapterText chapterText = new ChapterText();
            chapterText.setAuthor(rowSet.getString("author"));
            chapterText.setChapterNumber(rowSet.getInt("chapter"));
            chapterText.setChapterText(rowSet.getString("chapter_text"));
            result.add(chapterText);
        }
        return result;
    }
}
