package string_search_algorithms.naive;

import com.sun.istack.internal.NotNull;
import string_search_algorithms.IStringSearchAlgoStrategy;
import string_search_algorithms.naive.strategies.INaiveInternalStrategy;

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
