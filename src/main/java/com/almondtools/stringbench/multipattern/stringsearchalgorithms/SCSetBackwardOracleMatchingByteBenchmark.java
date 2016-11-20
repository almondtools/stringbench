package com.almondtools.stringbench.multipattern.stringsearchalgorithms;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.bytes.SetBackwardOracleMatching;
import net.amygdalum.stringsearchalgorithms.search.bytes.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCSetBackwardOracleMatchingByteBenchmark extends StringSearchAlgorithmsMultiByteBenchmark {

	private static final String ID = "StringSearchAlgorithms Set Backward Oracle Matching";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public StringSearchAlgorithm create(List<String> pattern) {
		return new SetBackwardOracleMatching(pattern, UTF_8);
	}

}
