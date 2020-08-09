package selfeducation.itaycohen;

import selfeducation.itaycohen.string_search.IStringSearchAlgorithm;
import selfeducation.itaycohen.string_search.StringSearchAlgorithm;
import selfeducation.itaycohen.string_search.strategies.IStringSearchStrategy;
import selfeducation.itaycohen.string_search.strategies.StringSearchStrategyFactory;

import java.util.List;

import static selfeducation.itaycohen.string_search.strategies.IStringSearchStrategy.Factory.*;

public class Main {

    public static void main(String[] args) {
	    String text = "AABAACAADAABAABA";
	    String pattern = "AABA";
        IStringSearchStrategy.Factory algFactory = new StringSearchStrategyFactory();
        IStringSearchAlgorithm stringSearchAlg = new StringSearchAlgorithm(algFactory.create(TYPE_KMP, PROPERTY_ASCENDING));

        List<Integer> matches = stringSearchAlg.search(pattern, text);
        System.out.println(matches);
    }
}
