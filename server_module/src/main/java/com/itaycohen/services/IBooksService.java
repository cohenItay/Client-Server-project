package com.itaycohen.services;

import com.itaycohen.dm.Book;
import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.BookWithSearch;
import com.itaycohen.dm.IBook;

public interface IBooksService {

    Book getBook(BookParams param);
    Book[] getBooks(BookParams[] params);
    BookWithSearch getBookWithSearch(BookParams param);
    BookWithSearch[] getBooksWithSearch(BookParams[] params);

    /**
     * saves a book of updating an exisiting book.
     * @param params required params to save.
     * @return true if save/update was successful
     */
    boolean saveBooks(BookParams[] params);

    /**
     * delete a book.
     * @param params required params to delete.
     * @return true if the deletion was successful
     */
    boolean deleteBooks(BookParams[] params);

    /**
     * @return list of all books exists in database (without their contents)
     */
    IBook[] peekBooks();
}
