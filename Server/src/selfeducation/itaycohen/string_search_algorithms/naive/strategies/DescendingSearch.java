package selfeducation.itaycohen.string_search_algorithms.naive.strategies;

import selfeducation.itaycohen.string_search_algorithms.strategies_contracts.IDirectionalSearchStrategy;

import java.util.ArrayList;
import java.util.List;

public class DescendingSearch<T extends CharSequence> implements IDirectionalSearchStrategy<T> {

    /**
     * Search strategy which searches a pattern inside a text, in <b>descending</b> way.
     * Meaning, the <b>last</b> match found will be in the <b>first</b> {@link List} Node.
     */
    @Override
    public List<Integer> search(T pattern, T text) {
        List<Integer> matches = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();
        int i,j;

        if (m > n)
            return matches;

        for (i=(n-m); i>=0; i--) {
            for (j=0; j<m; j++) {
                if (pattern.charAt(j) != text.charAt(i+j))
                    break;
            }
            if (j == m)
                matches.add(i);
        }
        return matches;
    }
}
