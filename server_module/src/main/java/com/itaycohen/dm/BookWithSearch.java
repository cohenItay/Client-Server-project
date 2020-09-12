package com.itaycohen.dm;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

public class BookWithSearch extends Book{

    private final @NotNull SearchResult searchResult;

    public BookWithSearch(String title, String content, @NotNull SearchResult searchResult) {
        super(title, content);
        Objects.requireNonNull(searchResult);
        this.searchResult = searchResult;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    @Override
    public String toString() {
        return "BookWithSearch{" +
                super.toString() +
                ", " +
                "searchResult=" + searchResult +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookWithSearch that = (BookWithSearch) o;
        return searchResult.equals(that.searchResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), searchResult);
    }
}

