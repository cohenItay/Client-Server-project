package com.itaycohen.data_layer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.IBook;
import com.itaycohen.dm.Params;
import com.itaycohen.utils.GsonContainer;
import com.itaycohen.utils.GsonIBookTypeAdapter;
import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

/**
 * The DataSource of which belongs to a Repository (used in Repository-pattern)
 * Its job is to handle to logic of how to load data from the internet for the remote library.
 */
public class LibraryInternetWebService implements IBooksInternetService {

    private static final String CONTENT_END = "content_end";
    private final int serverPort;
    private final String host;
    private final Gson gson;

    public LibraryInternetWebService(
            int serverPort,
            @NotNull String host,
            @NotNull Gson gson
    ) {
        this.serverPort = serverPort;
        Objects.requireNonNull(host);
        this.host = host;
        this.gson = GsonContainer.getInstance().newBuilder()
                .registerTypeAdapter(IBook.class, new GsonIBookTypeAdapter())
                .create();
    }

    @Override
    public CompletableFuture<Response<IBook[]>> getBooks(BookParams[] bookParams) {
        return CompletableFuture.supplyAsync(() ->
                sendServerRequestBlocking(new Params(bookParams, false), Request.Header.Values.GET, IBook[].class)
        );
    }

    @Override
    public CompletableFuture<Response<IBook[]>> peekAllBooks() {
        return CompletableFuture.supplyAsync(() ->
                sendServerRequestBlocking(new Params(null, true), Request.Header.Values.GET, IBook[].class)
        );
    }

    @Override
    public CompletableFuture<Response<IBook[]>> getBooksWithSearch(BookParams[] bookParams) {
        return CompletableFuture.supplyAsync(() ->
                sendServerRequestBlocking(new Params(bookParams, false), Request.Header.Values.GET, IBook[].class)
        );
    }

    @Override
    public CompletableFuture<Response<Void>> saveBooks(BookParams[] bookParams) {
        return CompletableFuture.supplyAsync(() ->
                sendServerRequestBlocking(new Params(bookParams, false), Request.Header.Values.UPDATE, Void.class)
        );
    }

    @Override
    public CompletableFuture<Response<Void>> deleteBook(BookParams[] bookParams) {
        return CompletableFuture.supplyAsync(() ->
                sendServerRequestBlocking(new Params(bookParams, false), Request.Header.Values.DELETE, Void.class)
        );
    }

    private <T> Response<T> sendServerRequestBlocking(@NotNull Params params, String action, Class<T> modelType) {
        Objects.requireNonNull(params);
        try {
            InetAddress addr = InetAddress.getByName(host);
            Socket clientSocket = new Socket(addr, serverPort);

            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
            Map<String, String> headers = new HashMap<>();
            headers.put(Request.Header.Keys.ACTION, action);
            Request<Params> request = new Request<>(headers, params);
            Type type = new TypeToken<Request<Params>>() {}.getType();
            String outJson = gson.toJson(request, type);
            printWriter.write(outJson);
            printWriter.write("\n");
            printWriter.write(CONTENT_END);
            printWriter.write("\n");
            printWriter.flush();

            Scanner scanner = new Scanner(clientSocket.getInputStream());
            StringBuilder inJsonBuilder = new StringBuilder();
            while (scanner.hasNextLine())
                inJsonBuilder.append(scanner.nextLine());

            printWriter.close();
            type = TypeToken.getParameterized(Response.class, modelType).getType();
            return gson.fromJson(inJsonBuilder.toString(), type);
        } catch (UnknownHostException e) {
            System.out.println("TODO: handle UnknownHostException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("TODO: handle IOException");
            e.printStackTrace();
        }
        return null;
    }
}
