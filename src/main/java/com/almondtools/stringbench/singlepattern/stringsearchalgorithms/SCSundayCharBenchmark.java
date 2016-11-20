package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.chars.StringSearchAlgorithm;
import net.amygdalum.stringsearchalgorithms.search.chars.Sunday;

@State(Scope.Thread)
public class SCSundayCharBenchmark extends StringSearchAlgorithmsCharBenchmark {

	private static final String ID = "StringSearchAlgorithms Sunday";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new Sunday(pattern);
	}

}
