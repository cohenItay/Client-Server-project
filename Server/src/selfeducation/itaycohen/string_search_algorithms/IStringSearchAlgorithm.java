package selfeducation.itaycohen.string_search_algorithms;

import java.util.List;

/**
 * A contract to the String Search algorithm family.
 * @param <T> Any {@link CharSequence} type which can be searched in.
 */
public interface IStringSearchAlgorithm<T extends CharSequence> {

    /**
     * Search all occurrences of {@code pattern} inside the {@code text}.
     * @param pattern Which you would like to search
     * @param text The total text which pattern should be searched inside.
     * @return {@link List} of {@link Integer} which contains the indices of starting index for a match.
     * If the array is empty, no match was found.
     */
    List<Integer> search(T pattern, T text);
}
