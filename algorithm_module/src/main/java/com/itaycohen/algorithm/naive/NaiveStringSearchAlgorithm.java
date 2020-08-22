package com.itaycohen.algorithm.naive;

import com.itaycohen.algorithm.IStringSearchAlgoStrategy;
import com.itaycohen.algorithm.naive.strategies.INaiveInternalStrategy;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 *  A naive Search algorithm. The algorithm is not designed to be efficient, only to supply the search outcome.
 */
public class NaiveStringSearchAlgorithm implements IStringSearchAlgoStrategy {

    private final INaiveInternalStrategy searchStrategy;

    public NaiveStringSearchAlgorithm(
            @NotNull INaiveInternalStrategy searchStrategy
    ) {
        this.searchStrategy = searchStrategy;
    }

    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        return searchStrategy.search(pattern, text);
    }

}
