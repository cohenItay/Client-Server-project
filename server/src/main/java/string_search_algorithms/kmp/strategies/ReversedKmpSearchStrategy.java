package string_search_algorithms.kmp.strategies;

import com.sun.istack.internal.NotNull;
import string_search_algorithms.naive.strategies.INaiveStringSearchStrategy;

import java.util.List;

public class ReversedKmpSearchStrategy implements IKmpStringSearchStrategy {

    private final IKmpDirectionalSearchStrategy directionalSearchStrategy;

    public ReversedKmpSearchStrategy(
            @NotNull IKmpDirectionalSearchStrategy directionalSearchStrategy
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
