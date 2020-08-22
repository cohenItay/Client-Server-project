import org.junit.Assert;
import org.junit.Test;
import string_search_algorithms.IStringSearchAlgoStrategy;
import string_search_algorithms.StringSearchFactory;

import java.util.Arrays;
import java.util.List;

import static string_search_algorithms.IStringSearchAlgoStrategy.Factory.*;

public class IStringSearchAlgorithmTest {

    private IStringSearchAlgoStrategy.Factory algFactory = new StringSearchFactory();
    private String text = "AABAACAADAABAABA";
    private String pattern = "AABA";

    @Test
    public void kmpTest() {
        IStringSearchAlgoStrategy alg = algFactory.create(TYPE_KMP, PROPERTY_DESCENDING);
        List<Integer> matches = alg.search(pattern, text);
        List<Integer> expected = Arrays.asList(12,9,0);
        Assert.assertEquals(expected, matches);
    }

    @Test
    public void naiveTest() {
        IStringSearchAlgoStrategy alg = algFactory.create(TYPE_NAIVE, PROPERTY_ASCENDING);
        List<Integer> matches = alg.search(pattern, text);
        List<Integer> expected = Arrays.asList(0,9,12);
        Assert.assertEquals(expected, matches);
    }
}
