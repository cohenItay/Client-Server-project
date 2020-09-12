package com.itaycohen.services;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itaycohen.algorithm.IStringSearchAlgoStrategy;
import com.itaycohen.dao.IDao;
import com.itaycohen.dm.SearchParams;
import com.itaycohen.dm.SearchResult;
import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SearchService implements IService {

    private final Gson gson;
    private @NotNull IStringSearchAlgoStrategy searchStrategy;
    private final IDao repoDao;

    public SearchService(@NotNull IStringSearchAlgoStrategy searchStrategy, IDao repoDao, Gson gson) {
        Objects.requireNonNull(searchStrategy);
        this.searchStrategy = searchStrategy;
        this.repoDao = repoDao;
        this.gson = gson;
    }


    @Override
    public void handleClient(final Socket clientSocket) {
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
            SearchParams params = gson.fromJson(inJsonBuilder.toString(), SearchParams.class);

            // Do the search
            SearchResult searchResult = search(params);

            // Return the outcome
            printWriter = new PrintWriter(clientSocket.getOutputStream());
            String outJson = gson.toJson(searchResult);
            System.out.println("service sending output: " + outJson);
            printWriter.write(outJson);
            printWriter.write("\n"); // end of transmission
            printWriter.flush();

        } catch (IOException | JsonSyntaxException e) {
            System.out.println("TODO: handle IOException | JsonSyntaxException");
            e.printStackTrace();
        } finally {
            // Free resources
            if (scanner != null)
                scanner.close();
            if (printWriter != null)
                printWriter.close();
        }
    }

    private SearchResult search(SearchParams params) {
        String fileContent = repoDao.readFileContent(params.getDataSourceFileName());
        List<Integer> occurrencesList = (fileContent.isEmpty() || params.getPattern().isEmpty()) ?
                new ArrayList<>(0) :
                searchStrategy.search(params.getPattern(), fileContent);
        return new SearchResult(params, occurrencesList);
    }
}
