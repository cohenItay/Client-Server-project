package com.itaycohen.service;

import com.itaycohen.algorithm.IStringSearchAlgoStrategy;
import com.itaycohen.dao.DaoFileImpl;
import com.itaycohen.dao.IDao;
import com.itaycohen.dm.SearchParams;
import com.itaycohen.dm.SearchResult;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchService implements ISearchService {

    private @NotNull IStringSearchAlgoStrategy searchStrategy;
    private final IDao repoDao;

    public SearchService(@NotNull IStringSearchAlgoStrategy searchStrategy, IDao repoDao) {
        Objects.requireNonNull(searchStrategy);
        this.searchStrategy = searchStrategy;
        this.repoDao = repoDao;
    }

    @Override
    public SearchResult search(SearchParams params) {
        String fileContent = repoDao.readFileContent(params.getDataSourceFileName());
        List<Integer> occurrencesList = (fileContent.isEmpty() || params.getPattern().isEmpty()) ?
                new ArrayList<>(0) :
                searchStrategy.search(params.getPattern(), repoDao.readFileContent(params.getDataSourceFileName()));
        return new SearchResult(params, occurrencesList);
    }
}
