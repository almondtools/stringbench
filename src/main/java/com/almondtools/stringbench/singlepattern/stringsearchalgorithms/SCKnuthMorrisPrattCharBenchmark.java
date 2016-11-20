package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.chars.KnuthMorrisPratt;
import net.amygdalum.stringsearchalgorithms.search.chars.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCKnuthMorrisPrattCharBenchmark extends StringSearchAlgorithmsCharBenchmark {

	private static final String ID = "StringSearchAlgorithms Knuth-Morris-Pratt";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new KnuthMorrisPratt(pattern);
	}

}
