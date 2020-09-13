package com.itaycohen.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itaycohen.ServerDriver;
import com.itaycohen.dm.Book;
import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.IBook;
import com.itaycohen.dm.SearchParams;
import com.itaycohen.dm.SearchResult;
import com.itaycohen.server.IHandleRequest;
import com.itaycohen.server.Request;
import com.itaycohen.server.Response;
import com.itaycohen.server.Server;
import com.itaycohen.utils.GsonContainer;
import com.itaycohen.utils.GsonIBookTypeAdapter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SearchServerTest {

    private static final String TEST_PATTERN = "AABA";
    private static final String TEST_BOOK_NAME = "test_book.txt";
    private static final String TEST_BOOK_CONTENT = "AABAACAADAABAABA";
    private final Gson gson = GsonContainer.getInstance().newBuilder()
            .registerTypeAdapter(IBook.class, new GsonIBookTypeAdapter())
            .create();
    private Server server;

    @Before
    public void doBefore() {
        server = ServerDriver.drive();
        try {
            Thread.sleep(500); // Let the serverThread run and start server resources..
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBooks() {
        IBook[] result = null;
        BookParams[] bookParamsArr = new BookParams[] {
                new BookParams(TEST_BOOK_NAME)
        };
        IBook[] expectedBookArr = new IBook[] {
                new Book(TEST_BOOK_NAME, TEST_BOOK_CONTENT)
        };
        System.out.println("expected = " + Arrays.toString(expectedBookArr));

        result = sendServerRequestBlocking(bookParamsArr, Request.Header.Values.GET);

        System.out.println("result = "  + Arrays.toString(result));
        Assert.assertArrayEquals(expectedBookArr, result);
    }

    @Test
    public void getBooksWithSearch() {
        /*SearchResult result = null;
        BookParams searchParams = new BookParams("test_book.txt", TEST_PATTERN);
        SearchResult expected = new SearchResult(
                searchParams,
                Arrays.asList(0,9,12)
        );
        System.out.println("expected = " + expected);


        result = sendServerRequestBlocking(searchParams);

        System.out.println("result = "  + result);
        Assert.assertEquals(result, expected);*/
    }

    @After
    public void doAfter() {
        server.shutDown();
    }

    private IBook[] sendServerRequestBlocking(BookParams[] bookParamsArr, String action) {
        try {
            InetAddress addr = InetAddress.getByName("localhost");
            Socket clientSocket = new Socket(addr, ServerDriver.PORT);

            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
            Map<String, String> headers = new HashMap<>();
            headers.put(Request.Header.Keys.ACTION, action);
            Request<BookParams[]> request = new Request<>(headers, bookParamsArr);
            Type type = new TypeToken<Request<BookParams[]>>() {}.getType();
            String outJson = gson.toJson(request, type);
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
            type = new TypeToken<Response<IBook[]>>() {}.getType();
            Response<IBook[]> response = gson.fromJson(inJsonBuilder.toString(), type);
            if (response != null)
                return response.getResponseBody();
            else
                return null;
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
