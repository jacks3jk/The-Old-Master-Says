package com.example.daodejing.model;

public class Quote {

    private String quoteString;

    private String firstWord;
    private String lastWord;
    private int middleLength;
    private String translator;
    private String fullText;

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public int getMiddleLength() {
        return middleLength;
    }

    public void setMiddleLength(int middleLength) {
        this.middleLength = middleLength;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }

    public String getLastWord() {
        return lastWord;
    }

    public void setLastWord(String lastWord) {
        this.lastWord = lastWord;
    }

    public String getQuoteString() {
        return quoteString;
    }

    public void setQuoteString(String quoteString) {
        this.quoteString = quoteString;
    }
}
