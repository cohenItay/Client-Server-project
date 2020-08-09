package selfeducation.itaycohen.string_search.strategies.kmp.internal_strategies;

import java.util.ArrayList;
import java.util.List;

public class AscendingKmpSearchStrategy implements IKmpDirectionalSearchStrategy {

    /**
     * Search strategy which searches a pattern inside a text, in <b>ascending</b> way.
     * Meaning, the <b>first</b> match found will be in the <b>first</b> {@link List} Node.
     */
    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        List<Integer> matches = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();
        int i = 0;
        int j = 0;
        int[] lps = LpsUtilSingleton.getInstance().createPreProcessedLps(pattern);

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == m) {
                matches.add(i-j);
                j = lps[j - 1];
            }

            else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return matches;
    }
}
