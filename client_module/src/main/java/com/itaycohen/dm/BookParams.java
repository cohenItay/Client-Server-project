package com.itaycohen.dm;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class BookParams implements Serializable {

    private final String title;
    private final String searchPattern;
    private final String content;

    public BookParams(@NotNull String title) throws IllegalStateException {
        this(title, null, null);
    }

    public BookParams(@NotNull String title, @NotNull String searchPattern) throws IllegalStateException {
        this(title, searchPattern, null);
    }

    public BookParams(
            @NotNull String title,
            @Nullable String searchPattern,
            @Nullable String content
    ) throws IllegalStateException {
        Objects.requireNonNull(title);
        if (title.isEmpty())
            throw new IllegalStateException("title cannot be empty");

        this.title = title;
        this.searchPattern = searchPattern;
        this.content = content;
    }

    public String getBookTitle() {
        return title;
    }

    public String getSearchPattern() {
        return searchPattern;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "BookParams{" +
                "title='" + title + '\'' +
                ", pattern='" + searchPattern + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookParams that = (BookParams) o;
        return title.equals(that.title) &&
                Objects.equals(searchPattern, that.searchPattern) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, searchPattern, content);
    }
}
