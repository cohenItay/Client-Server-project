package selfeducation.itaycohen.string_search_algorithms.naive;

import com.sun.istack.internal.NotNull;
import selfeducation.itaycohen.string_search_algorithms.IStringSearchAlgorithm;
import selfeducation.itaycohen.string_search_algorithms.strategies_contracts.IStringSearchStrategy;

import java.util.List;

/**
 *  A naive Search algorithm. The algorithm is not desgined to be efficient, only to supply the search outcome.
 */
public class NaiveStringSearchAlgorithm<T extends CharSequence> implements IStringSearchAlgorithm<T> {

    private final IStringSearchStrategy<T> searchStrategy;

    public NaiveStringSearchAlgorithm(
            @NotNull IStringSearchStrategy<T> searchStrategy
    ) {
        this.searchStrategy = searchStrategy;
    }

    @Override
    public List<Integer> search(T pattern, T text) {
        return searchStrategy.search(pattern, text);
    }

}
