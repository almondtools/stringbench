package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.chars.Horspool;
import net.amygdalum.stringsearchalgorithms.search.chars.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCHorspoolRelaxedCharBenchmark extends StringSearchAlgorithmsCharBenchmark {

	private static final String ID = "StringSearchAlgorithms Horspool (non-strict shifts)";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new Horspool(pattern, true);
	}

}
