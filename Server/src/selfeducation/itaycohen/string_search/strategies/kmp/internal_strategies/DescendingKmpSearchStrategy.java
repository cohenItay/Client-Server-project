package selfeducation.itaycohen.string_search.strategies.kmp.internal_strategies;

import java.util.ArrayList;
import java.util.List;

public class DescendingKmpSearchStrategy implements IKmpDirectionalSearchStrategy {

    /**
     * Search strategy which searches a pattern inside a text, in <b>descending</b> way.
     * Meaning, the <b>last</b> match found will be in the <b>first</b> {@link List} Node.
     */
    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        List<Integer> matches = new ArrayList<>();
        // TODO: implement
        return matches;
    }
}
