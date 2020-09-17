package com.itaycohen.data_layer;

import com.itaycohen.data_layer.dm.BookParams;
import com.itaycohen.data_layer.dm.IBook;

import javax.xml.ws.Response;
import java.util.concurrent.Future;

/**
 * A Repository-pattern class.
 * this class job is to manage his request with its {@link DataSource}s, as from which data source the data should arrive.
 */
public interface RemoteLibraryRepository {

    Future<Response<IBook[]>> requestModel(BookParams[] params);
}
