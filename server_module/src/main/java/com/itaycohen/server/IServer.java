package com.itaycohen.server;

public interface IServer {

    /**
     * Starting a worker thread which is listening for server request.
     */
    void run();

    /**
     * Shuts down the service. it can take up to predefined time to be shut down.
     */
    void shutDown();
}
