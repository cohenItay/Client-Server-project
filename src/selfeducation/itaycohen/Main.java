package selfeducation.itaycohen;

import selfeducation.itaycohen.string_search_algorithms.IStringSearchAlgorithm;
import selfeducation.itaycohen.string_search_algorithms.naive.NaiveStringSearchAlgorithm;
import selfeducation.itaycohen.string_search_algorithms.naive.strategies.DescendingSearch;
import selfeducation.itaycohen.string_search_algorithms.naive.strategies.ReverseSearch;
import selfeducation.itaycohen.string_search_algorithms.strategies_contracts.IDirectionalSearchStrategy;
import selfeducation.itaycohen.string_search_algorithms.strategies_contracts.IStringSearchStrategy;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    String text = "AABAACAADAABAABA";
	    String pattern = "AABA";

        IDirectionalSearchStrategy<CharSequence> directionalStrategy = new DescendingSearch<>();
        IStringSearchStrategy<CharSequence> searchStrategy = new ReverseSearch(directionalStrategy);
        IStringSearchAlgorithm<CharSequence> alg = new NaiveStringSearchAlgorithm<>(searchStrategy);
        List<Integer> matches = alg.search(pattern, text);
        System.out.println(matches);
    }
}
