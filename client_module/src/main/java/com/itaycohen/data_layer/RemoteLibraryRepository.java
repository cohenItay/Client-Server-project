package com.itaycohen.data_layer;

import com.itaycohen.data_layer.dm.BookParams;
import com.itaycohen.data_layer.dm.IBook;


import java.util.concurrent.CompletableFuture;

/**
 * A Repository-pattern class.
 * this class job is to manage his request with its DataSources, as from which data source the data should arrive.
 */
public interface RemoteLibraryRepository {

    CompletableFuture<Response<IBook[]>> requestBooks(BookParams[] params);
    CompletableFuture<Response<Void>> uploadBooks(BookParams[] params);
    CompletableFuture<Response<Void>> deleteBooks(BookParams[] params);
}
