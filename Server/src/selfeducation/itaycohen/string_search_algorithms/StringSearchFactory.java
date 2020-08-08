package selfeducation.itaycohen.string_search_algorithms;

import selfeducation.itaycohen.string_search_algorithms.kmp.KmpStringSearchAlgorithm;
import selfeducation.itaycohen.string_search_algorithms.kmp.strategies.AscendingKmpSearchStrategy;
import selfeducation.itaycohen.string_search_algorithms.kmp.strategies.DescendingKmpSearchStrategy;
import selfeducation.itaycohen.string_search_algorithms.kmp.strategies.IKmpStringSearchStrategy;
import selfeducation.itaycohen.string_search_algorithms.kmp.strategies.ReversedKmpSearchStrategy;
import selfeducation.itaycohen.string_search_algorithms.naive.NaiveStringSearchAlgorithm;
import selfeducation.itaycohen.string_search_algorithms.naive.strategies.AscendingNaiveSearchStrategy;
import selfeducation.itaycohen.string_search_algorithms.naive.strategies.DescendingNaiveSearchStrategy;
import selfeducation.itaycohen.string_search_algorithms.naive.strategies.INaiveStringSearchStrategy;
import selfeducation.itaycohen.string_search_algorithms.naive.strategies.ReversedNaiveSearchStrategy;

public class StringSearchFactory implements IStringSearchAlgorithm.Factory {

    @Override
    public IStringSearchAlgorithm create(int algorithmTypeId, int algorithmPropertyId) {
        switch (algorithmTypeId) {

            case IStringSearchAlgorithm.Factory.TYPE_NAIVE: {
                switch (algorithmPropertyId) {
                    case IStringSearchAlgorithm.Factory.PROPERTY_ASCENDING:
                        return new NaiveStringSearchAlgorithm(new AscendingNaiveSearchStrategy());
                    case IStringSearchAlgorithm.Factory.PROPERTY_DESCENDING:
                        return new NaiveStringSearchAlgorithm(new DescendingNaiveSearchStrategy());
                    case IStringSearchAlgorithm.Factory.PROPERTY_REVERSED_ASCENDING:
                        INaiveStringSearchStrategy reverseAscStrategy = new ReversedNaiveSearchStrategy(new AscendingNaiveSearchStrategy());
                        return new NaiveStringSearchAlgorithm(reverseAscStrategy);
                    case IStringSearchAlgorithm.Factory.PROPERTY_REVERSED_DESCENDING:
                        INaiveStringSearchStrategy reverseDescStrategy = new ReversedNaiveSearchStrategy(new DescendingNaiveSearchStrategy());
                        return new NaiveStringSearchAlgorithm(reverseDescStrategy);
                    default:
                        throw new IllegalArgumentException("algorithmPropertyId value = " + algorithmPropertyId + " is incorrect");
                }
            }

            case IStringSearchAlgorithm.Factory.TYPE_KMP: {
                switch (algorithmPropertyId) {
                    case IStringSearchAlgorithm.Factory.PROPERTY_ASCENDING:
                        return new KmpStringSearchAlgorithm(new AscendingKmpSearchStrategy());
                    case IStringSearchAlgorithm.Factory.PROPERTY_DESCENDING:
                        return new KmpStringSearchAlgorithm(new DescendingKmpSearchStrategy());
                    case IStringSearchAlgorithm.Factory.PROPERTY_REVERSED_ASCENDING:
                        IKmpStringSearchStrategy reverseAscStrategy = new ReversedKmpSearchStrategy(new AscendingKmpSearchStrategy());
                        return new KmpStringSearchAlgorithm(reverseAscStrategy);
                    case IStringSearchAlgorithm.Factory.PROPERTY_REVERSED_DESCENDING:
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
