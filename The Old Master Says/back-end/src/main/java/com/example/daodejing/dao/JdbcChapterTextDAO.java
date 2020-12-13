package com.example.daodejing.dao;

import com.example.daodejing.model.ChapterText;
import com.example.daodejing.model.Quote;
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
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT ct.chapter, ct.chapter_text, t.author, t.text_id " +
                "FROM chapter_text AS ct " +
                "JOIN translation AS t ON ct.text_id = t.text_id " +
                "WHERE ct.chapter = ?;", chapterNumber);
        while (rowSet.next()) {
            ChapterText chapterText = new ChapterText();
            chapterText.setAuthor(rowSet.getString("author"));
            chapterText.setChapterNumber(rowSet.getInt("chapter"));
            chapterText.setChapterText(rowSet.getString("chapter_text"));
            chapterText.setTextId(rowSet.getInt("text_id"));
            result.add(chapterText);
        }
        return result;
    }

    @Override
    public List<ChapterText> returnAllTranslations() {
        List<ChapterText> result = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(
                "SELECT text_id, author, publish_year " +
                        "FROM translation;");
        while (rowSet.next()) {
            ChapterText chapterText = new ChapterText();
            chapterText.setTextId(rowSet.getInt("text_id"));
            chapterText.setAuthor(rowSet.getString("author"));
            chapterText.setPublishYear(rowSet.getInt("publish_year"));
            result.add(chapterText);
        }
        return result;
    }
    @Override
    public List<Quote> returnAllMatching(String searchText) {
        List<Quote> result = new ArrayList<>();
        Quote userQuote = searchToQuote(searchText);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT trans.author, t.chapter, t.chapter_text " +
                        "FROM chapter_text AS t JOIN translation AS trans ON t.text_id = trans.text_id " +
                        "WHERE chapter_text_tokens @@ to_tsquery(?);",
                userQuote.getFirstWord() + " <" + userQuote.getMiddleLength() + "> " + userQuote.getLastWord());
        while (rowSet.next()) {
            Quote newQuote = new Quote();
            newQuote.setTranslator(rowSet.getString("author"));
            newQuote.setChapterText(rowSet.getString("chapter_text"));
            newQuote.setChapterNumber(rowSet.getInt("chapter"));
            result.add(newQuote);
        }
        return result;
    }

    @Override
    public int returnWordCount(String searchText) {
        int result = 0;
        Quote userQuote = searchToQuote(searchText);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT nentry " +
                        "FROM ts_stat('SELECT chapter_text_tokens FROM chapter_text') " +
                        "WHERE ts_stat.word = ?;", searchText);

        result = rowSet.getInt("nentry");

        return result;
    }

    private Quote searchToQuote (String searchText) {
        Quote result = new Quote();
        String firstWord = searchText.substring(0, searchText.indexOf(' '));
        result.setFirstWord(firstWord);
        String lastWord = searchText.substring(searchText.lastIndexOf(' '));
        result.setLastWord(lastWord);
        String middle = searchText.substring(searchText.indexOf(' '), searchText.lastIndexOf(' '));
        int count = 0;
        for (int i = 0; i < searchText.length(); i++) {
            if (searchText.charAt(i) == ' ') {
                count ++;
            }
        }
        result.setMiddleLength(count - 1);
        return result;
    }

}
