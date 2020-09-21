package com.itaycohen.services;

import com.google.gson.Gson;
import com.itaycohen.algorithm.IStringSearchAlgoStrategy;
import com.itaycohen.dao.IDao;
import com.itaycohen.dm.Book;
import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.BookWithSearch;
import com.itaycohen.dm.IBook;
import com.itaycohen.dm.SearchParams;
import com.itaycohen.dm.SearchResult;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BooksService implements IBooksService{

    private @NotNull IStringSearchAlgoStrategy searchStrategy;
    private final IDao repoDao;
    private final String parentPath = System.getProperty("user.dir")+"/server_module/src/main/resources/books";

    public BooksService(@NotNull IStringSearchAlgoStrategy searchStrategy, IDao repoDao) {
        Objects.requireNonNull(searchStrategy);
        this.searchStrategy = searchStrategy;
        this.repoDao = repoDao;
    }


    @Override
    public IBook[] peekBooks() {
        String[] filesNames = repoDao.readAllTextFilesNamesIn(parentPath);
        if (filesNames == null || filesNames.length < 1)
            return new IBook[0];
        else {
            IBook[] books = new IBook[filesNames.length];
            for (int i=0; i<filesNames.length; i++) {
                String name = filesNames[i];
                books[i] = new Book(name, null);
            }
            return books;
        }
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
        String bookContent = repoDao.readFromFile(parentPath, param.getBookTitle());
        if (bookContent == null || bookContent.isEmpty())
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
        String bookContent = repoDao.readFromFile(parentPath, param.getBookTitle());
        if (bookContent == null || bookContent.isEmpty())
            bookContent = "No such book";
        return new Book(param.getBookTitle(), bookContent);
    }


    @Override
    public boolean saveBooks(BookParams[] params) {
        boolean isAllSuccess = true;
        for (BookParams param : params)
            isAllSuccess &= repoDao.saveToFile(parentPath, param.getBookTitle(), param.getContent());
        return isAllSuccess;
    }

    @Override
    public boolean deleteBooks(BookParams[] params) {
        boolean isAllSuccess = true;
        for (BookParams param : params)
            isAllSuccess &= repoDao.deleteFile(parentPath, param.getBookTitle());
        return isAllSuccess;
    }

    private SearchResult search(String fileContent, BookParams params) {
        List<Integer> occurrencesList = (fileContent.isEmpty() || params.getBookTitle().isEmpty()) ?
                new ArrayList<>(0) :
                searchStrategy.search(params.getSearchPattern(), fileContent);
        return new SearchResult(new SearchParams(params), occurrencesList);
    }
}
