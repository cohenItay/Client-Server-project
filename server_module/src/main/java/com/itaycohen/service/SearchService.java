package com.itaycohen.service;

import com.itaycohen.algorithm.IStringSearchAlgoStrategy;
import com.itaycohen.dao.DaoFileImpl;
import com.itaycohen.dao.IDao;
import com.itaycohen.dm.SearchResult;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchService {

    private static final String FILE_NAME = "datasource.txt";
    private @NotNull IStringSearchAlgoStrategy searchStrategy;
    private final IDao textRepo;

    public SearchService(@NotNull IStringSearchAlgoStrategy searchStrategy) {
        Objects.requireNonNull(searchStrategy);
        this.searchStrategy = searchStrategy;
        this.textRepo = new DaoFileImpl(FILE_NAME);
    }

    public SearchResult search(String pattern) {
        String fileContent = textRepo.readFileContent();
        List<Integer> occurrencesList = (fileContent.isEmpty() || pattern.isEmpty()) ?
                new ArrayList<>(0) :
                searchStrategy.search(pattern, textRepo.readFileContent());
        return new SearchResult(pattern, occurrencesList);
    }
}
