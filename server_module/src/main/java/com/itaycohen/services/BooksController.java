package com.itaycohen.services;


import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.IBook;
import com.sun.istack.internal.NotNull;

public class BooksController implements IController<IBook[], BookParams[]> {

    private final @NotNull IBooksService booksService;

    public BooksController(@NotNull IBooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public IBook[] onGet(BookParams[] params) {
        return booksService.getBooks(params);
    }

    @Override
    public void onUpdate(BookParams[] params) {

    }

    @Override
    public void onDelete(BookParams[] params) {

    }
}