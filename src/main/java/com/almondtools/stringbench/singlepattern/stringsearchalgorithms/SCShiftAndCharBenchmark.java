package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.chars.ShiftAnd;
import net.amygdalum.stringsearchalgorithms.search.chars.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCShiftAndCharBenchmark extends StringSearchAlgorithmsCharBenchmark {

	private static final String ID = "StringSearchAlgorithms Shift-And";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new ShiftAnd(pattern);
	}

}
