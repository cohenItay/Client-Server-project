package com.itaycohen.data_layer;

import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.IBook;
import com.sun.istack.internal.NotNull;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class LibraryRepository implements RemoteLibraryRepository {

    private final IBooksInternetService internetService;

    public LibraryRepository(
            @NotNull IBooksInternetService internetService
    ) {
        Objects.requireNonNull(internetService);
        this.internetService = internetService;
    }

    @Override
    public CompletableFuture<Response<IBook[]>> requestBooks(BookParams[] params) {
        return internetService.getBooks(params);
    }

    @Override
    public CompletableFuture<Response<IBook[]>> peekAllBooks() {
        return internetService.peekAllBooks();
    }

    @Override
    public CompletableFuture<Response<Void>> uploadBooks(BookParams[] params) {
        return internetService.saveBooks(params);
    }

    @Override
    public CompletableFuture<Response<Void>> deleteBooks(BookParams[] params) {
        return internetService.deleteBook(params);
    }
}
