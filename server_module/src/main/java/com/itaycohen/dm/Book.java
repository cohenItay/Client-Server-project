package com.itaycohen.dm;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

public class Book implements IBook {

    private final String title;
    private String content;

    public Book(@NotNull String title, String content) {
        Objects.requireNonNull(title);
        this.title = title;
        this.content = content;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) &&
                Objects.equals(content, book.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content);
    }
}
