package selfeducation.itaycohen.string_search.strategies.naive;

import com.sun.istack.internal.NotNull;
import selfeducation.itaycohen.string_search.strategies.IStringSearchStrategy;
import selfeducation.itaycohen.string_search.strategies.naive.internal_strategies.INaiveInternalStrategy;

import java.util.List;

/**
 *  A naive Search algorithm. The algorithm is not designed to be efficient, only to supply the search outcome.
 */
public class NaiveStringSearchStrategy implements IStringSearchStrategy {

    private final INaiveInternalStrategy searchStrategy;

    public NaiveStringSearchStrategy(
            @NotNull INaiveInternalStrategy searchStrategy
    ) {
        this.searchStrategy = searchStrategy;
    }

    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        return searchStrategy.search(pattern, text);
    }

}
