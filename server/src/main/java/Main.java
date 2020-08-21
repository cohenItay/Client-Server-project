import string_search_algorithms.IStringSearchAlgorithm;
import string_search_algorithms.StringSearchFactory;

import java.util.List;
import static string_search_algorithms.IStringSearchAlgorithm.Factory.*;

public class Main {

    public static void main(String[] args) {
	    String text = "AABAACAADAABAABA";
	    String pattern = "AABA";
        IStringSearchAlgorithm.Factory algFactory = new StringSearchFactory();
        IStringSearchAlgorithm alg = algFactory.create(TYPE_KMP, PROPERTY_DESCENDING);

        List<Integer> matches = alg.search(pattern, text);
        System.out.println(matches);
    }
}
