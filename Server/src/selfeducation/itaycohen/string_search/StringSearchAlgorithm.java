package selfeducation.itaycohen.string_search;

import java.util.List;

import selfeducation.itaycohen.string_search.strategies.IStringSearchStrategy;

public class StringSearchAlgorithm implements IStringSearchAlgorithm {

    private final IStringSearchStrategy algStrategy;

    public StringSearchAlgorithm(IStringSearchStrategy algStrategy) {
        this.algStrategy = algStrategy;
    }

    @Override
    public List<Integer> search(CharSequence pattern, CharSequence text) {
        return algStrategy.search(pattern, text);
    }
}
