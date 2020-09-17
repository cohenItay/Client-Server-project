package com.itaycohen.services;

import com.itaycohen.data_layer.dm.Book;
import com.itaycohen.data_layer.dm.BookParams;
import com.itaycohen.data_layer.dm.BookWithSearch;

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
}
