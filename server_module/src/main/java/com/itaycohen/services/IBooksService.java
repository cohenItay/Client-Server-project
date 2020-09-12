package com.itaycohen.services;

import com.itaycohen.dm.Book;
import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.BookWithSearch;
import com.itaycohen.dm.IBook;

public interface IBooksService {

    Book[] getBooks(BookParams[] params);
    BookWithSearch[] getBooksWithSearch(BookParams[] params);
}
