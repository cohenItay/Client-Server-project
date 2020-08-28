package com.itaycohen.service;

import com.itaycohen.dm.SearchParams;
import com.itaycohen.dm.SearchResult;

public interface ISearchService {

    SearchResult search(SearchParams params);
}
