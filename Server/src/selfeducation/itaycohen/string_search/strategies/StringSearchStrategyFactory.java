package selfeducation.itaycohen.string_search.strategies;

import selfeducation.itaycohen.string_search.strategies.kmp.KmpStringSearchStrategy;
import selfeducation.itaycohen.string_search.strategies.kmp.internal_strategies.AscendingKmpSearchStrategy;
import selfeducation.itaycohen.string_search.strategies.kmp.internal_strategies.DescendingKmpSearchStrategy;
import selfeducation.itaycohen.string_search.strategies.kmp.internal_strategies.IKmpInternalStrategy;
import selfeducation.itaycohen.string_search.strategies.kmp.internal_strategies.ReversedKmpSearchStrategy;
import selfeducation.itaycohen.string_search.strategies.naive.NaiveStringSearchStrategy;
import selfeducation.itaycohen.string_search.strategies.naive.internal_strategies.AscendingNaiveSearchStrategy;
import selfeducation.itaycohen.string_search.strategies.naive.internal_strategies.DescendingNaiveSearchStrategy;
import selfeducation.itaycohen.string_search.strategies.naive.internal_strategies.INaiveInternalStrategy;
import selfeducation.itaycohen.string_search.strategies.naive.internal_strategies.ReversedNaiveSearchStrategy;

public class StringSearchStrategyFactory implements IStringSearchStrategy.Factory {

    @Override
    public IStringSearchStrategy create(int algorithmTypeId, int algorithmPropertyId) {
        switch (algorithmTypeId) {

            case IStringSearchStrategy.Factory.TYPE_NAIVE: {
                switch (algorithmPropertyId) {
                    case IStringSearchStrategy.Factory.PROPERTY_ASCENDING:
                        return new NaiveStringSearchStrategy(new AscendingNaiveSearchStrategy());
                    case IStringSearchStrategy.Factory.PROPERTY_DESCENDING:
                        return new NaiveStringSearchStrategy(new DescendingNaiveSearchStrategy());
                    case IStringSearchStrategy.Factory.PROPERTY_REVERSED_ASCENDING:
                        INaiveInternalStrategy reverseAscStrategy = new ReversedNaiveSearchStrategy(new AscendingNaiveSearchStrategy());
                        return new NaiveStringSearchStrategy(reverseAscStrategy);
                    case IStringSearchStrategy.Factory.PROPERTY_REVERSED_DESCENDING:
                        INaiveInternalStrategy reverseDescStrategy = new ReversedNaiveSearchStrategy(new DescendingNaiveSearchStrategy());
                        return new NaiveStringSearchStrategy(reverseDescStrategy);
                    default:
                        throw new IllegalArgumentException("algorithmPropertyId value = " + algorithmPropertyId + " is incorrect");
                }
            }

            case IStringSearchStrategy.Factory.TYPE_KMP: {
                switch (algorithmPropertyId) {
                    case IStringSearchStrategy.Factory.PROPERTY_ASCENDING:
                        return new KmpStringSearchStrategy(new AscendingKmpSearchStrategy());
                    case IStringSearchStrategy.Factory.PROPERTY_DESCENDING:
                        return new KmpStringSearchStrategy(new DescendingKmpSearchStrategy());
                    case IStringSearchStrategy.Factory.PROPERTY_REVERSED_ASCENDING:
                        IKmpInternalStrategy reverseAscStrategy = new ReversedKmpSearchStrategy(new AscendingKmpSearchStrategy());
                        return new KmpStringSearchStrategy(reverseAscStrategy);
                    case IStringSearchStrategy.Factory.PROPERTY_REVERSED_DESCENDING:
                        IKmpInternalStrategy reverseDescStrategy = new ReversedKmpSearchStrategy(new DescendingKmpSearchStrategy());
                        return new KmpStringSearchStrategy(reverseDescStrategy);
                    default:
                        throw new IllegalArgumentException("algorithmPropertyId value = " + algorithmPropertyId + " is incorrect");
                }
            }

            default:
                throw new IllegalArgumentException("algorithmId value = " + algorithmTypeId + " is incorrect");

        }
    }
}
