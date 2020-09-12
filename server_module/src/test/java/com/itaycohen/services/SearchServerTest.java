package com.itaycohen.services;

import com.google.gson.Gson;
import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.SearchParams;
import com.itaycohen.dm.SearchResult;
import com.itaycohen.server.IHandleRequest;
import com.itaycohen.server.Server;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class SearchServerTest {

    private static final String TEST_PATTERN = "AABA";
    private static final String DATA_SOURCE_FILE_NAME = "dataSource.txt";
    private static final int PORT = 12345;
    private final Gson gson = new Gson();

    @Test
    public void runTest() {
        SearchResult result = null;
        BookParams searchParams = new BookParams(TEST_PATTERN, DATA_SOURCE_FILE_NAME);
        SearchResult expected = new SearchResult(
                searchParams,
                Arrays.asList(0,9,12)
        );
        System.out.println("expected = " + expected);

        Server server = new Server(PORT);
        server.run();
        try {
            Thread.sleep(500); // Let the serverThread run and start server resources..
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = sendServerRequestBlocking(searchParams);
        server.shutDown();

        System.out.println("result = "  + result);
        Assert.assertEquals(result, expected);
    }

    private SearchResult sendServerRequestBlocking(BookParams searchParams) {
        try {
            InetAddress addr = InetAddress.getByName("localhost");
            Socket clientSocket = new Socket(addr, PORT);

            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
            String outJson = gson.toJson(searchParams);
            printWriter.write(outJson);
            printWriter.write("\n");
            printWriter.write(IHandleRequest.CONTENT_END);
            printWriter.write("\n");
            printWriter.flush();

            Scanner scanner = new Scanner(clientSocket.getInputStream());
            StringBuilder inJsonBuilder = new StringBuilder();
            while (scanner.hasNextLine())
                inJsonBuilder.append(scanner.nextLine());

            printWriter.close();
            return gson.fromJson(inJsonBuilder.toString(), SearchResult.class);
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
