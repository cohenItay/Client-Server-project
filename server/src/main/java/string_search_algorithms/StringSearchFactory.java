package string_search_algorithms;

import string_search_algorithms.kmp.KmpStringSearchAlgorithm;
import string_search_algorithms.kmp.strategies.directional.AscendingKmpSearchStrategy;
import string_search_algorithms.kmp.strategies.directional.DescendingKmpSearchStrategy;
import string_search_algorithms.kmp.strategies.IKmpInternalStrategy;
import string_search_algorithms.kmp.strategies.ReversedKmpSearchStrategy;
import string_search_algorithms.naive.NaiveStringSearchAlgorithm;
import string_search_algorithms.naive.strategies.directional.AscendingNaiveSearchStrategy;
import string_search_algorithms.naive.strategies.directional.DescendingNaiveSearchStrategy;
import string_search_algorithms.naive.strategies.INaiveInternalStrategy;
import string_search_algorithms.naive.strategies.ReversedNaiveSearchStrategy;

public class StringSearchFactory implements IStringSearchAlgoStrategy.Factory {

    @Override
    public IStringSearchAlgoStrategy create(int algorithmTypeId, int algorithmPropertyId) {
        switch (algorithmTypeId) {

            case TYPE_NAIVE: {
                switch (algorithmPropertyId) {
                    case PROPERTY_ASCENDING:
                        return new NaiveStringSearchAlgorithm(new AscendingNaiveSearchStrategy());
                    case PROPERTY_DESCENDING:
                        return new NaiveStringSearchAlgorithm(new DescendingNaiveSearchStrategy());
                    case PROPERTY_REVERSED_ASCENDING:
                        INaiveInternalStrategy reverseAscStrategy = new ReversedNaiveSearchStrategy(new AscendingNaiveSearchStrategy());
                        return new NaiveStringSearchAlgorithm(reverseAscStrategy);
                    case PROPERTY_REVERSED_DESCENDING:
                        INaiveInternalStrategy reverseDescStrategy = new ReversedNaiveSearchStrategy(new DescendingNaiveSearchStrategy());
                        return new NaiveStringSearchAlgorithm(reverseDescStrategy);
                    default:
                        throw new IllegalArgumentException("algorithmPropertyId value = " + algorithmPropertyId + " is incorrect");
                }
            }

            case TYPE_KMP: {
                switch (algorithmPropertyId) {
                    case PROPERTY_ASCENDING:
                        return new KmpStringSearchAlgorithm(new AscendingKmpSearchStrategy());
                    case PROPERTY_DESCENDING:
                        return new KmpStringSearchAlgorithm(new DescendingKmpSearchStrategy());
                    case PROPERTY_REVERSED_ASCENDING:
                        IKmpInternalStrategy reverseAscStrategy = new ReversedKmpSearchStrategy(new AscendingKmpSearchStrategy());
                        return new KmpStringSearchAlgorithm(reverseAscStrategy);
                    case PROPERTY_REVERSED_DESCENDING:
                        IKmpInternalStrategy reverseDescStrategy = new ReversedKmpSearchStrategy(new DescendingKmpSearchStrategy());
                        return new KmpStringSearchAlgorithm(reverseDescStrategy);
                    default:
                        throw new IllegalArgumentException("algorithmPropertyId value = " + algorithmPropertyId + " is incorrect");
                }
            }

            default:
                throw new IllegalArgumentException("algorithmId value = " + algorithmTypeId + " is incorrect");

        }
    }
}
