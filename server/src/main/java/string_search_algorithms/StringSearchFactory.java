package string_search_algorithms;

import string_search_algorithms.kmp.KmpStringSearchAlgorithm;
import string_search_algorithms.kmp.strategies.AscendingKmpSearchStrategy;
import string_search_algorithms.kmp.strategies.DescendingKmpSearchStrategy;
import string_search_algorithms.kmp.strategies.IKmpStringSearchStrategy;
import string_search_algorithms.kmp.strategies.ReversedKmpSearchStrategy;
import string_search_algorithms.naive.NaiveStringSearchAlgorithm;
import string_search_algorithms.naive.strategies.AscendingNaiveSearchStrategy;
import string_search_algorithms.naive.strategies.DescendingNaiveSearchStrategy;
import string_search_algorithms.naive.strategies.INaiveStringSearchStrategy;
import string_search_algorithms.naive.strategies.ReversedNaiveSearchStrategy;

public class StringSearchFactory implements IStringSearchAlgorithm.Factory {

    @Override
    public IStringSearchAlgorithm create(int algorithmTypeId, int algorithmPropertyId) {
        switch (algorithmTypeId) {

            case TYPE_NAIVE: {
                switch (algorithmPropertyId) {
                    case PROPERTY_ASCENDING:
                        return new NaiveStringSearchAlgorithm(new AscendingNaiveSearchStrategy());
                    case PROPERTY_DESCENDING:
                        return new NaiveStringSearchAlgorithm(new DescendingNaiveSearchStrategy());
                    case PROPERTY_REVERSED_ASCENDING:
                        INaiveStringSearchStrategy reverseAscStrategy = new ReversedNaiveSearchStrategy(new AscendingNaiveSearchStrategy());
                        return new NaiveStringSearchAlgorithm(reverseAscStrategy);
                    case PROPERTY_REVERSED_DESCENDING:
                        INaiveStringSearchStrategy reverseDescStrategy = new ReversedNaiveSearchStrategy(new DescendingNaiveSearchStrategy());
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
                        IKmpStringSearchStrategy reverseAscStrategy = new ReversedKmpSearchStrategy(new AscendingKmpSearchStrategy());
                        return new KmpStringSearchAlgorithm(reverseAscStrategy);
                    case PROPERTY_REVERSED_DESCENDING:
                        IKmpStringSearchStrategy reverseDescStrategy = new ReversedKmpSearchStrategy(new DescendingKmpSearchStrategy());
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
