package com.almondtools.stringbench.scalingpattern.stringsearchalgorithms;

import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.chars.QGramShiftOr;
import net.amygdalum.stringsearchalgorithms.search.chars.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCQGramShiftOrCharBenchmark extends StringSearchAlgorithmsScalingCharBenchmark {

	private static final String ID = "StringSearchAlgorithms QGram-Shift-Or";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(List<String> pattern) {
		return new QGramShiftOr(pattern);
	}

}
