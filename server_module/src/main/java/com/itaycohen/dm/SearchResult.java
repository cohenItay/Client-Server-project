package com.itaycohen.dm;

import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Objects;

public class SearchResult {

    private @NotNull String pattern;
    private @NotNull List<Integer> patternOccurrences;

    public SearchResult(
            @NotNull String pattern,
            @NotNull List<Integer> patternOccurrences
    ) {
        this.pattern = pattern;
        this.patternOccurrences = patternOccurrences;
    }

    public String getPattern() {
        return pattern;
    }

    public List<Integer> getPatternOccurrences() {
        return patternOccurrences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return pattern.equals(that.pattern) &&
                patternOccurrences.equals(that.patternOccurrences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, patternOccurrences);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "pattern='" + pattern + '\'' +
                ", patternOccurrences=" + patternOccurrences +
                '}';
    }
}
