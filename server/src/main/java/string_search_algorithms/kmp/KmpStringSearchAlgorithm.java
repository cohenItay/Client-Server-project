package string_search_algorithms.kmp;

import com.sun.istack.internal.NotNull;
import string_search_algorithms.IStringSearchAlgorithm;
import string_search_algorithms.kmp.strategies.IKmpStringSearchStrategy;

import java.util.List;

/**
 * one of the most efficient algorithms to search all occurrences of a pattern inside a text.
 * O(n) will be the worst case scenario.
 */
public class KmpStringSearchAlgorithm implements IStringSearchAlgorithm {

    private final IKmpStringSearchStrategy searchStrategy;

    public KmpStringSearchAlgorithm(
            @NotNull IKmpStringSearchStrategy searchStrategy
    ) {
        this.searchStrategy = searchStrategy;
    }

    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        return searchStrategy.search(pattern, text);
    }
}
