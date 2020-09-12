package com.itaycohen.services;

import java.net.Socket;

/**
 * Provide some service to a client.
 */
public interface IService {

    String CONTENT_END = "content_end";

    /**
     * Handle the client request.
     * @param clientSocket represent the client.
     */
    void handleClient(Socket clientSocket);
}
