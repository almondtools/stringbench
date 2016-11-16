package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.KnuthMorrisPratt;
import net.amygdalum.stringsearchalgorithms.search.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCKnuthMorrisPrattBenchmark extends StringSearchAlgorithmsBenchmark {

	private static final String ID = "Strings and Chars Knuth-Morris-Pratt";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new KnuthMorrisPratt(pattern);
	}

}
