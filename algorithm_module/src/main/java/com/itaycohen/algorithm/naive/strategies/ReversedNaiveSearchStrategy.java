package com.itaycohen.algorithm.naive.strategies;

import com.sun.istack.internal.NotNull;
import com.itaycohen.algorithm.naive.strategies.directional.INaiveInternalDirectionalStrategy;

import java.util.List;

public class ReversedNaiveSearchStrategy implements INaiveInternalStrategy {

    private final INaiveInternalDirectionalStrategy directionalSearchStrategy;

    public ReversedNaiveSearchStrategy(
            @NotNull INaiveInternalDirectionalStrategy directionalSearchStrategy
    ) {
        this.directionalSearchStrategy = directionalSearchStrategy;
    }

    /**
     * Search strategy which searches the supplied {@code pattern} <b>reversed</b> inside a text.
     */
    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        CharSequence reversedPattern = new StringBuilder(pattern).reverse().toString();
        return directionalSearchStrategy.search(reversedPattern, text);
    }
}
