package string_search_algorithms.naive.strategies;

import java.util.List;

/**
 * Specify <b>how</b> the search will be executed in the implementing Strategy.
 * */
public interface INaiveInternalStrategy {

    /**
     * Search all occurrences of {@code pattern} inside the {@code text}.
     * @param pattern Which you would like to search
     * @param text The total text which pattern should be searched inside.
     * @return {@link List} of {@link Integer} which contains the indices of starting index for a match.
     * If the array is empty, no match was found.
     */
    List<Integer> search(CharSequence pattern, CharSequence text);
}
