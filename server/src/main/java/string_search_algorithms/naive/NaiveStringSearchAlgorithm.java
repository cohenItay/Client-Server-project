package string_search_algorithms.naive;

import com.sun.istack.internal.NotNull;
import string_search_algorithms.IStringSearchAlgorithm;
import string_search_algorithms.naive.strategies.INaiveStringSearchStrategy;

import java.util.List;

/**
 *  A naive Search algorithm. The algorithm is not designed to be efficient, only to supply the search outcome.
 */
public class NaiveStringSearchAlgorithm implements IStringSearchAlgorithm {

    private final INaiveStringSearchStrategy searchStrategy;

    public NaiveStringSearchAlgorithm(
            @NotNull INaiveStringSearchStrategy searchStrategy
    ) {
        this.searchStrategy = searchStrategy;
    }

    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        return searchStrategy.search(pattern, text);
    }

}
