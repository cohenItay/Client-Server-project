package com.itaycohen.services;


import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.IBook;
import com.itaycohen.dm.RequestBodyParams;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class BooksController implements IController<IBook[], RequestBodyParams> {

    private final @NotNull IBooksService booksService;

    public BooksController(@NotNull IBooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public IBook[] onGet(RequestBodyParams bodyParams) {
        IBook[] books = null;
        if (bodyParams.isPeek()) {
            books = booksService.peekBooks();
        } else if (!bodyParams.isPeek() && bodyParams.getBookParams() != null && bodyParams.getBookParams().length > 0) {
            BookParams[] bookParams = bodyParams.getBookParams();
            books = new IBook[bookParams.length];
            for (int i = 0; i < bookParams.length; i++) {
                BookParams param = bookParams[i];
                if (param.getSearchPattern() != null && !param.getSearchPattern().isEmpty())
                    books[i] = booksService.getBookWithSearch(param);
                else
                    books[i] = booksService.getBook(param);
            }
        }
        return books;
    }

    @Override
    public boolean onUpdate(RequestBodyParams bodyParams) {
        return booksService.saveBooks(bodyParams.getBookParams());
    }

    @Override
    public boolean onDelete(RequestBodyParams bodyParams) {
        return booksService.deleteBooks(bodyParams.getBookParams());
    }
}