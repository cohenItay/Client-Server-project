package com.itaycohen.services;


import com.itaycohen.data_layer.dm.BookParams;
import com.itaycohen.data_layer.dm.IBook;
import com.sun.istack.internal.NotNull;

public class BooksController implements IController<IBook[], BookParams[]> {

    private final @NotNull IBooksService booksService;

    public BooksController(@NotNull IBooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public IBook[] onGet(BookParams[] params) {
        IBook[] books = new IBook[params.length];
        for(int i=0; i<params.length; i++) {
            BookParams param = params[i];
            if (param.getSearchPattern() != null && !param.getSearchPattern().isEmpty())
                books[i] = booksService.getBookWithSearch(param);
            else
                books[i] = booksService.getBook(param);
        }
        return books;
    }

    @Override
    public boolean onUpdate(BookParams[] params) {
        return booksService.saveBooks(params);
    }

    @Override
    public boolean onDelete(BookParams[] params) {
        return booksService.deleteBooks(params);
    }
}