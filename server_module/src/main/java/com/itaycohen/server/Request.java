package com.itaycohen.server;

import com.sun.istack.internal.Nullable;

import java.util.Map;
import java.util.Objects;

/**
 * Request data model to be sent to server
 * @param <T> the expected request body type
 */
public class Request<T> {

    private Map<String, String> headers;
    private T requestBody;

    public Request(Map<String, String> header, @Nullable T requestBody) {
        Objects.requireNonNull(header);
        this.headers = header;
        this.requestBody = requestBody;
    }

    public Map<String, String> getHeader() {
        return headers;
    }

    public T getRequestBody() {
        return requestBody;
    }



    public static class Header {

        public static class Keys {
            public static final String ACTION = "action";
        }

        public static class Values {
            public static final String GET = "GET";
            public static final String UPDATE = "UPDATE";
            public static final String DELETE = "DELETE";
        }
    }
}
