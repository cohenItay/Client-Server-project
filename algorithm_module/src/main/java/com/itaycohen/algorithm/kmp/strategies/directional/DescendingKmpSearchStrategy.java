package com.itaycohen.algorithm.kmp.strategies.directional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DescendingKmpSearchStrategy implements IKmpInternalDirectionalStrategy {

    /**
     * Search strategy which searches a pattern inside a text, in <b>descending</b> way.
     * Meaning, the <b>last</b> match found will be in the <b>first</b> {@link List} Node.
     */
    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        List<Integer> matches = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();
        int i = 0;
        int j = 0;
        int[] lps = createPreProcessedLps(pattern);

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
        Collections.reverse(matches);
        return matches;
    }

    public int[] createPreProcessedLps(CharSequence pattern) {
        int patternLength = pattern.length();
        int[] lps = new int[patternLength];
        int properPrefixAmount = 0;
        int i = 1;

        lps[0] = 0;
        while (i < patternLength) {
            if (pattern.charAt(i) == pattern.charAt(properPrefixAmount)) {
                properPrefixAmount++;
                lps[i] = properPrefixAmount;
                i++;
            }
            else {
                if (properPrefixAmount != 0) {
                    properPrefixAmount = lps[properPrefixAmount - 1];
                } else {
                    lps[i] = properPrefixAmount;
                    i++;
                }
            }
        }
        return lps;
    }
}
