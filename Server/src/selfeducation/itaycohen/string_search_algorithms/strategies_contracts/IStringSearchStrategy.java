package selfeducation.itaycohen.string_search_algorithms.strategies_contracts;

import selfeducation.itaycohen.string_search_algorithms.IStringSearchAlgorithm;

/**
 * Specify <b>how</b> the search will be executed in the implementing Strategy.
 * */
public interface IStringSearchStrategy<T extends CharSequence> extends IStringSearchAlgorithm<T> { }
