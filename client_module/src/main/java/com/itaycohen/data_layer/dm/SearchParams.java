package com.itaycohen.data_layer.dm;

public class SearchParams {

    private final String title;
    private final String searchPattern;

    public SearchParams(BookParams bookParams) {
        this(bookParams.getBookTitle(), bookParams.getSearchPattern());
    }

    public SearchParams(String title, String searchPattern) {
        this.title = title;
        this.searchPattern = searchPattern;
    }

    public String getBookTitle() {
        return title;
    }

    public String getSearchPattern() {
        return searchPattern;
    }
}
