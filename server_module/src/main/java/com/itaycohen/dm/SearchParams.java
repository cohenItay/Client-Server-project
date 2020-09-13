package com.itaycohen.dm;

public class SearchParams {

    private final String title;
    private final String searchPattern;

    public SearchParams(BookParams bookParams) {
        this.title = bookParams.getBookTitle();
        this.searchPattern = bookParams.getSearchPattern();
    }

    public String getBookTitle() {
        return title;
    }

    public String getSearchPattern() {
        return searchPattern;
    }
}
