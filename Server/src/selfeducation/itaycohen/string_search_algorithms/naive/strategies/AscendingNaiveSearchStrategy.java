package selfeducation.itaycohen.string_search_algorithms.naive.strategies;

import java.util.ArrayList;
import java.util.List;

public class AscendingNaiveSearchStrategy implements INaiveDirectionalSearchStrategy {

    /**
     * Search strategy which searches a pattern inside a text, in <b>ascending</b> way.
     * Meaning, the <b>first</b> match found will be in the <b>first</b> {@link List} Node.
     */
    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        List<Integer> matches = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();
        int i,j;

        if (m > n)
            return matches;

        for (i=0; i<=(n-m); i++) {
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
