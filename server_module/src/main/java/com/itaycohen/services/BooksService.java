package com.itaycohen.services;

import com.google.gson.Gson;
import com.itaycohen.algorithm.IStringSearchAlgoStrategy;
import com.itaycohen.dao.IDao;
import com.itaycohen.dm.Book;
import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.BookWithSearch;
import com.itaycohen.dm.SearchResult;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BooksService implements IBooksService{

    private final Gson gson;
    private @NotNull IStringSearchAlgoStrategy searchStrategy;
    private final IDao repoDao;

    public BooksService(@NotNull IStringSearchAlgoStrategy searchStrategy, IDao repoDao, Gson gson) {
        Objects.requireNonNull(searchStrategy);
        this.searchStrategy = searchStrategy;
        this.repoDao = repoDao;
        this.gson = gson;
    }

    @Override
    public Book[] getBooks(BookParams[] params) {
        Book[] books = new Book[params.length];
        for (int i=0; i<params.length; i++) {
            BookParams bookParams = params[i];
            String bookContent = repoDao.readFileContent(bookParams.getBookTitle());
            if (bookContent.isEmpty())
                bookContent = "No such book";
            books[i] = new Book(bookParams.getBookTitle(), bookContent);
        }
        return books;
    }

    @Override
    public BookWithSearch[] getBooksWithSearch(BookParams[] params) {
        BookWithSearch[] books = new BookWithSearch[params.length];
        for (int i=0; i<params.length; i++) {
            BookParams bookParams = params[i];
            SearchResult searchResult = null;
            String bookContent = repoDao.readFileContent(bookParams.getBookTitle());
            if (bookContent.isEmpty())
                bookContent = "No such book";
            else
                searchResult = search(bookContent, bookParams);
            books[i] = new BookWithSearch(bookParams.getBookTitle(), bookContent, searchResult);
        }
        return books;
    }

    private SearchResult search(String fileContent, BookParams params) {
        List<Integer> occurrencesList = (fileContent.isEmpty() || params.getBookTitle().isEmpty()) ?
                new ArrayList<>(0) :
                searchStrategy.search(params.getBookTitle(), fileContent);
        return new SearchResult(params, occurrencesList);
    }
}
