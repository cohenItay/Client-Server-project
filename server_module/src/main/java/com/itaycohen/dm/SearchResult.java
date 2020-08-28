package com.itaycohen.dm;

import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Objects;

public class SearchResult {

    private @NotNull SearchParams searchParams;
    private @NotNull List<Integer> patternOccurrences;

    public SearchResult(
            @NotNull SearchParams searchParams,
            @NotNull List<Integer> patternOccurrences
    ) {
        this.searchParams = searchParams;
        this.patternOccurrences = patternOccurrences;
    }

    public SearchParams getSearchParams() {
        return searchParams;
    }

    public List<Integer> getPatternOccurrences() {
        return patternOccurrences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return searchParams.equals(that.searchParams) &&
                patternOccurrences.equals(that.patternOccurrences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchParams, patternOccurrences);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "pattern='" + searchParams + '\'' +
                ", patternOccurrences=" + patternOccurrences +
                '}';
    }
}
