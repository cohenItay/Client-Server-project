package string_search_algorithms;

import java.util.List;

/**
 * A contract to the String Search algorithm family.
 */
public interface IStringSearchAlgorithm {

    /**
     * Search all occurrences of {@code pattern} inside the {@code text}.
     * @param pattern Which you would like to search
     * @param text The total text which pattern should be searched inside.
     * @return {@link List} of {@link Integer} which contains the indices of starting index for a match.
     * If the array is empty, no match was found.
     */
    List<Integer> search(CharSequence pattern, CharSequence text);

    interface Factory {

        int PROPERTY_ASCENDING = 1;
        int PROPERTY_DESCENDING = 2;
        int PROPERTY_REVERSED_ASCENDING = 3;
        int PROPERTY_REVERSED_DESCENDING = 4;

        int TYPE_NAIVE = 1;
        int TYPE_KMP = 2;

        /**
         * Creates an instance of {@link IStringSearchAlgorithm}
         * @param algorithmTypeId The identifier of the algorithm.
         * @param algorithmPropertyId The identifier of the algorithm property, meaning how it will work.
         * @return new instance of {@link IStringSearchAlgorithm}
         */
         IStringSearchAlgorithm create(int algorithmTypeId, int algorithmPropertyId);
    }
}
