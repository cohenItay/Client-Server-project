package com.itaycohen.dm;

import com.sun.istack.internal.Nullable;

public class RequestBodyParams {

    private @Nullable BookParams[] bookParams;
    private boolean isPeek;

    public RequestBodyParams() {}

    public BookParams[] getBookParams() {
        return bookParams;
    }

    public boolean isPeek() {
        return isPeek;
    }
}
