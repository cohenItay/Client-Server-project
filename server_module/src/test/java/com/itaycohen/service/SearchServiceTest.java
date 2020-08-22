package com.itaycohen.service;

import com.itaycohen.algorithm.IStringSearchAlgoStrategy;
import com.itaycohen.algorithm.StringSearchFactory;
import com.itaycohen.dm.SearchResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.itaycohen.algorithm.IStringSearchAlgoStrategy.Factory.PROPERTY_ASCENDING;
import static com.itaycohen.algorithm.IStringSearchAlgoStrategy.Factory.TYPE_KMP;

public class SearchServiceTest {

    private static final String TEST_PATTERN = "AABA";

    @Test
    public void runTest() {
        SearchResult expected = new SearchResult(
                TEST_PATTERN,
                Arrays.asList(0,9,12)
        );
        System.out.println("expected = " + expected);

        IStringSearchAlgoStrategy searchAlg = new StringSearchFactory().create(TYPE_KMP, PROPERTY_ASCENDING);
        SearchService service = new SearchService(searchAlg);
        SearchResult result = service.search(TEST_PATTERN);
        System.out.println("result = "  + result);

        Assert.assertEquals(result, expected);
    }
}
