package com.itaycohen.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.IBook;
import com.itaycohen.services.IController;
import com.itaycohen.utils.GsonIBookTypeAdapter;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandleBookRequest implements IHandleRequest {

    private final Gson gson;
    private final IController<IBook[], BookParams[]> controller;

    public HandleBookRequest(
            Gson gson,
            IController<IBook[], BookParams[]> controller
    ) {
        this.gson = gson.newBuilder()
                .registerTypeAdapter(IBook.class, new GsonIBookTypeAdapter())
                .create();
        this.controller = controller;
    }

    @Override
    public void handleRequest(Socket clientSocket) {
        PrintWriter printWriter = null;
        Scanner scanner = null;
        boolean shouldKeepReading = true;

        try {
            // Read the request params
            scanner = new Scanner(clientSocket.getInputStream());
            StringBuilder inJsonBuilder = new StringBuilder();
            while (shouldKeepReading && scanner.hasNextLine()) { // Blocks thread until client closes stream connection
                String line = scanner.nextLine();
                if (CONTENT_END.equals(line)) {
                    shouldKeepReading = false;
                } else {
                    inJsonBuilder.append(line);
                }
            }

            // Business logic:
            Type type = new TypeToken<Request<BookParams[]>>() {}.getType();
            Request<BookParams[]> request = gson.fromJson(inJsonBuilder.toString(), type);
            String action = request.getHeader().get(Request.Header.Keys.ACTION);
            Map<String, String> responseHeader = new HashMap<>();
            responseHeader.put(Response.Header.Keys.RESPONSE_CODE, Response.Header.Values.OK); // start with OK.
            IBook[] books = null;
            switch (action) {
                case Request.Header.Values.GET:
                    books = controller.onGet(request.getRequestBody());
                    break;
                case Request.Header.Values.UPDATE:
                    controller.onUpdate(request.getRequestBody());
                    break;
                case Request.Header.Values.DELETE:
                    controller.onDelete(request.getRequestBody());
                    break;
                default:
                    responseHeader.put(Response.Header.Keys.RESPONSE_CODE, Response.Header.Values.ERROR);
                    throw new IllegalArgumentException("Unfamiliar action");
            }


            // Return the outcome
            Response<IBook[]> response = new Response<>(responseHeader, books);
            printWriter = new PrintWriter(clientSocket.getOutputStream());
            type = new TypeToken<Response<IBook[]>>() {}.getType();
            String outJson = gson.toJson(response, type);
            printWriter.write(outJson);
            printWriter.write("\n"); // end of transmission
            printWriter.flush();

        } catch (IOException | JsonSyntaxException e) {
            Logger.getGlobal().log(Level.SEVERE, "TODO: handle IOException | JsonSyntaxException", e);
        } finally {
            // Free resources
            if (scanner != null)
                scanner.close();
            if (printWriter != null)
                printWriter.close();
        }
    }
}
