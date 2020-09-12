package com.itaycohen.server;


import com.google.gson.Gson;
import com.itaycohen.algorithm.IStringSearchAlgoStrategy;
import com.itaycohen.algorithm.StringSearchFactory;
import com.itaycohen.dao.DaoFileImpl;
import com.itaycohen.services.IService;
import com.itaycohen.services.SearchService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.itaycohen.algorithm.IStringSearchAlgoStrategy.Factory.PROPERTY_ASCENDING;
import static com.itaycohen.algorithm.IStringSearchAlgoStrategy.Factory.TYPE_KMP;

public class Server implements IServer{

    private static Server serverInstance;
    private static final int SO_TIMEOUT = 5000;
    private static final int THREAD_POOL_SIZE = 5;

    public  static final int PORT = 12345;

    private boolean shouldAcceptClients = false;
    private ServerSocket serverSocket;
    private ExecutorService executor;
    private final IService service;

    private Server() {
        IStringSearchAlgoStrategy searchAlg = new StringSearchFactory().create(TYPE_KMP, PROPERTY_ASCENDING);
        service = new SearchService(searchAlg, new DaoFileImpl(), new Gson());
    }

    public static Server getInstance() {
        if (serverInstance == null)
            serverInstance = new Server();
        return serverInstance;
    }

    @Override
    public void run() {
        if (shouldAcceptClients) {
            System.out.println("Already Running...");
            return;
        }
        shouldAcceptClients = true;

        //init the Thread pool if needed
        if (executor == null || executor.isShutdown())
            executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try {
            serverSocket = new ServerSocket(PORT);
            //serverSocket.setSoTimeout(SO_TIMEOUT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        acceptClients();
    }

    /**
     * Blocking method which provide the client interaction of the server.
     */
    private void acceptClients() {
        while (serverSocket != null && !serverSocket.isClosed() && shouldAcceptClients) {
            try {
                final Socket clientSocket = serverSocket.accept();
                executor.execute(() -> {
                    service.handleClient(clientSocket);
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        System.out.println("closing client connection failure");
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                if (e instanceof SocketException)
                    Logger.getGlobal().log(Level.INFO, "Socket connection shutdown (could be by hand)");
                else
                    Logger.getGlobal().log(Level.SEVERE, "TCP open connection with client failure", e);
            }
        }
    }

    @Override
    public void shutDown() {
        shouldAcceptClients = false;
        if (serverSocket != null) {
            try {
                serverSocket.close();
                executor.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                serverSocket = null;
            }
        }
    }
}
