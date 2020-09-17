package com.itaycohen.data_layer;

import com.itaycohen.data_layer.dm.BookParams;
import com.itaycohen.data_layer.dm.IBook;

import javax.xml.ws.Response;
import java.util.concurrent.Future;

public interface IBooksInternetService {

    /**
     * Retrieve the desired book from internet.
     * @param params the book required parameters
     * @return promise to return a value of {@link IBook} array in the future
     */
    Future<Response<IBook[]>> getBooks(BookParams[] params);

    /**
     * Retrieve the desired book from internet and perform a search inside those books.
     * @param params the book required parameters with search response
     * @return promise to return a value of {@link IBook} array in the future
     */
    Future<Response<IBook[]>> getBooksWithSearch(BookParams[] params);

    /**
     * Save a new book into the remote library
     * @param params the required book parameters
     * @return response which its header keys indicate on the state of that request.
     */
    Future<Response<Void>> saveBook(BookParams[] params);

    /**
     * Delete the desired book from the remote library
     * @param params the required book parameters
     * @return response which its header keys indicate on the state of that request.
     */
    Future<Response<Void>> deleteBook(BookParams[] params);
}
