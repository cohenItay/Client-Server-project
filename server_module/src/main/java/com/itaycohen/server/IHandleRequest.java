package com.itaycohen.server;

import java.net.Socket;

public interface IHandleRequest {

    String CONTENT_END = "content_end";

    void handleRequest(Socket clientSocket);
}
