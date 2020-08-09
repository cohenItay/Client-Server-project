package selfeducation.itaycohen.string_search.strategies.kmp.internal_strategies;

class LpsUtilSingleton {

    private static LpsUtilSingleton instance;

    private LpsUtilSingleton() {}

    static LpsUtilSingleton getInstance() {
        if (instance == null)
            instance = new LpsUtilSingleton();
        return instance;
    }

    int[] createPreProcessedLps(CharSequence pattern) {
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
