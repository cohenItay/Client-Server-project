package com.itaycohen.data_layer;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Map;
import java.util.Objects;

public class Response<T> {

    private @NotNull Map<String, String> headers;
    private @Nullable T responseBody;

    public Response(Map<String, String> header, @Nullable T responseBody) {
        Objects.requireNonNull(header);
        this.headers = header;
        this.responseBody = responseBody;
    }

    public @NotNull Map<String, String> getHeaders() {
        return headers;
    }

    public @Nullable T getResponseBody() {
        return responseBody;
    }



    public static class Header {

        public static class Keys {
            public static final String RESPONSE_CODE = "response code";
        }

        public static class Values {
            public static final String OK = "OK";
            public static final String ERROR = "ERROR";
        }
    }
}
