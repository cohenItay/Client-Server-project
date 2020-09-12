package com.itaycohen.dm;

import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SearchResult implements Serializable {

    private @NotNull IBookSearchParams searchParams;
    private @NotNull List<Integer> patternOccurrences;

    public SearchResult(
        @NotNull IBookSearchParams searchParams,
        @NotNull List<Integer> patternOccurrences
    ) {
        this.searchParams = searchParams;
        this.patternOccurrences = patternOccurrences;
    }

    public IBookSearchParams getSearchParams() {
        return searchParams;
    }

    public List<Integer> getPatternOccurrences() {
        return patternOccurrences;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "searchParams=" + searchParams.getBookTitle() +
                " - " +
                searchParams.getSearchPattern() +
                " " +
                ", patternOccurrences=" + patternOccurrences +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return searchParams.getBookTitle().equals(that.searchParams.getBookTitle()) &&
                searchParams.getSearchPattern().equals(that.searchParams.getSearchPattern()) &&
                patternOccurrences.equals(that.patternOccurrences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchParams.getBookTitle(), searchParams.getSearchPattern(), patternOccurrences);
    }
}
