package com.example.daodejing.dao;

import com.example.daodejing.model.Quote;
import com.example.daodejing.model.Translation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcQuoteDAO implements QuoteDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcQuoteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Translation> returnAllTranslations() {
        List<Translation> result = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(
                "SELECT translation_id, author, publish_year " +
                        "FROM translation;");
        while (rowSet.next()) {
            Translation translation = new Translation();
            translation.setTranslationId(rowSet.getInt("translation_id"));
            translation.setAuthor(rowSet.getString("author"));
            translation.setPublishYear(rowSet.getInt("publish_year"));
            result.add(translation);
        }
        return result;
    }

    @Override
    public List<Quote> returnAllMatching(String searchText) {
        List<Quote> result = new ArrayList<>();
        Quote userQuote = searchToQuote(searchText);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT trans.author, t.full_text " +
                "FROM full_text AS t JOIN translation AS trans ON t.translation_id = trans.translation_id " +
                "WHERE full_text_tokens @@ to_tsquery(?);",
                userQuote.getFirstWord() + " <" + userQuote.getMiddleLength() + "> " + userQuote.getLastWord());
        while (rowSet.next()) {
            Quote newQuote = new Quote();
            newQuote.setTranslator(rowSet.getString("author"));
            newQuote.setFullText(rowSet.getString("full_text"));
            result.add(newQuote);
        }
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
