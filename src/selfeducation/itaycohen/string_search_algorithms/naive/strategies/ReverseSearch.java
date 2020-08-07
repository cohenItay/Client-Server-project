package selfeducation.itaycohen.string_search_algorithms.naive.strategies;

import com.sun.istack.internal.NotNull;
import selfeducation.itaycohen.string_search_algorithms.strategies_contracts.IDirectionalSearchStrategy;
import selfeducation.itaycohen.string_search_algorithms.strategies_contracts.IStringSearchStrategy;

import java.util.List;

public class ReverseSearch implements IStringSearchStrategy<CharSequence> {

    private final IDirectionalSearchStrategy<CharSequence> directionalSearchStrategy;

    public ReverseSearch(
            @NotNull IDirectionalSearchStrategy<CharSequence> directionalSearchStrategy
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
