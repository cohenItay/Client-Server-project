package string_search_algorithms.naive.strategies;

import com.sun.istack.internal.NotNull;

import java.util.List;

public class ReversedNaiveSearchStrategy implements INaiveStringSearchStrategy {

    private final INaiveDirectionalSearchStrategy directionalSearchStrategy;

    public ReversedNaiveSearchStrategy(
            @NotNull INaiveDirectionalSearchStrategy directionalSearchStrategy
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
