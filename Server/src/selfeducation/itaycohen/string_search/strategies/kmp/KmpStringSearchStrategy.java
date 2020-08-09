package selfeducation.itaycohen.string_search.strategies.kmp;

import com.sun.istack.internal.NotNull;
import selfeducation.itaycohen.string_search.strategies.IStringSearchStrategy;
import selfeducation.itaycohen.string_search.strategies.kmp.internal_strategies.IKmpInternalStrategy;

import java.util.List;

/**
 * one of the most efficient algorithms to search all occurrences of a pattern inside a text.
 * O(n) will be the worst case scenario.
 */
public class KmpStringSearchStrategy implements IStringSearchStrategy {

    private final IKmpInternalStrategy searchStrategy;

    public KmpStringSearchStrategy(
            @NotNull IKmpInternalStrategy searchStrategy
    ) {
        this.searchStrategy = searchStrategy;
    }

    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        return searchStrategy.search(pattern, text);
    }
}
