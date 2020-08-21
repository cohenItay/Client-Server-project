import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import string_search_algorithms.IStringSearchAlgorithm;
import string_search_algorithms.StringSearchFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static string_search_algorithms.IStringSearchAlgorithm.Factory.*;

public class IStringSearchAlgorithmTest {

    private IStringSearchAlgorithm.Factory algFactory = new StringSearchFactory();
    private String text = "AABAACAADAABAABA";
    private String pattern = "AABA";

    @Test
    public void kmpTest() {
        IStringSearchAlgorithm alg = algFactory.create(TYPE_KMP, PROPERTY_DESCENDING);
        List<Integer> matches = alg.search(pattern, text);
        List<Integer> expected = Arrays.asList(12,9,0);
        Assert.assertEquals(expected, matches);
    }

    @Test
    public void naiveTest() {
        IStringSearchAlgorithm alg = algFactory.create(TYPE_NAIVE, PROPERTY_ASCENDING);
        List<Integer> matches = alg.search(pattern, text);
        List<Integer> expected = Arrays.asList(0,9,12);
        Assert.assertEquals(expected, matches);
    }
}
