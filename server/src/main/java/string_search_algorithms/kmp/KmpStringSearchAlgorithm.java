package string_search_algorithms.kmp;

import com.sun.istack.internal.NotNull;
import string_search_algorithms.IStringSearchAlgoStrategy;
import string_search_algorithms.kmp.strategies.IKmpInternalStrategy;

import java.util.List;

/**
 * one of the most efficient algorithms to search all occurrences of a pattern inside a text.
 * O(n) will be the worst case scenario.
 */
public class KmpStringSearchAlgorithm implements IStringSearchAlgoStrategy {

    private final IKmpInternalStrategy searchStrategy;

    public KmpStringSearchAlgorithm(
            @NotNull IKmpInternalStrategy searchStrategy
    ) {
        this.searchStrategy = searchStrategy;
    }

    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        return searchStrategy.search(pattern, text);
    }
}
