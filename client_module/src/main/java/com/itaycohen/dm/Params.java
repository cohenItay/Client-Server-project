package com.itaycohen.dm;

import com.sun.istack.internal.Nullable;

public class Params {

    private final @Nullable BookParams[] bookParams;
    private final boolean isPeek;

    public Params(@Nullable BookParams[] bookParams, boolean isPeek) {
        this.bookParams = bookParams;
        this.isPeek = isPeek;
    }
}
