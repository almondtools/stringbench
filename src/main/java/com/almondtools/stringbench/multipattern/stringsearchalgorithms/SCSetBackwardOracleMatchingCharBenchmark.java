package com.almondtools.stringbench.multipattern.stringsearchalgorithms;

import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.chars.SetBackwardOracleMatching;
import net.amygdalum.stringsearchalgorithms.search.chars.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCSetBackwardOracleMatchingCharBenchmark extends StringSearchAlgorithmsMultiCharBenchmark {

	private static final String ID = "StringSearchAlgorithms Set Backward Oracle Matching";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public StringSearchAlgorithm create(List<String> pattern) {
		return new SetBackwardOracleMatching(pattern);
	}

}
