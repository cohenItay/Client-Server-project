package com.itaycohen.server;

import java.util.Map;

public class Request<T> {

    private Map<String, String> headers;
    private T requestBody;

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
