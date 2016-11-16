package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.StringSearchAlgorithm;
import net.amygdalum.stringsearchalgorithms.search.Sunday;

@State(Scope.Thread)
public class SCSundayRelaxedBenchmark extends StringSearchAlgorithmsBenchmark {

	private static final String ID = "Strings and Chars Sunday (non-strict shifts)";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new Sunday(pattern, true);
	}

}
