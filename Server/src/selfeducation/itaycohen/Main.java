package selfeducation.itaycohen;

import selfeducation.itaycohen.string_search_algorithms.IStringSearchAlgorithm;
import selfeducation.itaycohen.string_search_algorithms.StringSearchFactory;

import java.util.List;

import static selfeducation.itaycohen.string_search_algorithms.IStringSearchAlgorithm.Factory.*;

public class Main {

    public static void main(String[] args) {
	    String text = "AABAACAADAABAABA";
	    String pattern = "AABA";
        IStringSearchAlgorithm.Factory algFactory = new StringSearchFactory();
        IStringSearchAlgorithm alg = algFactory.create(TYPE_KMP, PROPERTY_ASCENDING);

        List<Integer> matches = alg.search(pattern, text);
        System.out.println(matches);
    }
}
