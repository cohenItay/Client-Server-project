package com.itaycohen.services;

import com.google.gson.Gson;
import com.itaycohen.algorithm.IStringSearchAlgoStrategy;
import com.itaycohen.dao.IDao;
import com.itaycohen.dm.Book;
import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.BookWithSearch;
import com.itaycohen.dm.SearchParams;
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
    public BookWithSearch[] getBooksWithSearch(BookParams[] params) {
        BookWithSearch[] books = new BookWithSearch[params.length];
        for (int i=0; i<params.length; i++) {
            BookParams bookParams = params[i];
            books[i] = getBookWithSearch(bookParams);
        }
        return books;
    }

    @Override
    public BookWithSearch getBookWithSearch(BookParams param) {
        SearchResult searchResult = null;
        String bookContent = repoDao.readFromFile(param.getBookTitle());
        if (bookContent.isEmpty())
            bookContent = "No such book";
        else
            searchResult = search(bookContent, param);
        return new BookWithSearch(param.getBookTitle(), bookContent, searchResult);
    }

    @Override
    public Book[] getBooks(BookParams[] params) {
        Book[] books = new Book[params.length];
        for (int i=0; i<params.length; i++) {
            BookParams bookParams = params[i];
            books[i] = getBook(bookParams);
        }
        return books;
    }

    @Override
    public Book getBook(BookParams param) {
        String bookContent = repoDao.readFromFile(param.getBookTitle());
        if (bookContent.isEmpty())
            bookContent = "No such book";
        return new Book(param.getBookTitle(), bookContent);
    }


    @Override
    public boolean saveBooks(BookParams[] params) {
        boolean isAllSuccess = true;
        for (BookParams param : params)
            isAllSuccess &= repoDao.saveToFile(param.getBookTitle(), param.getContent());
        return isAllSuccess;
    }

    @Override
    public boolean deleteBooks(BookParams[] params) {
        boolean isAllSuccess = true;
        for (BookParams param : params)
            isAllSuccess &= repoDao.deleteFile(param.getBookTitle());
        return isAllSuccess;
    }

    private SearchResult search(String fileContent, BookParams params) {
        List<Integer> occurrencesList = (fileContent.isEmpty() || params.getBookTitle().isEmpty()) ?
                new ArrayList<>(0) :
                searchStrategy.search(params.getSearchPattern(), fileContent);
        return new SearchResult(new SearchParams(params), occurrencesList);
    }
}
