package com.almondtools.stringbench.multipattern.stringsearchalgorithms;

import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.chars.StringSearchAlgorithm;
import net.amygdalum.stringsearchalgorithms.search.chars.WuManber;

@State(Scope.Thread)
public class SCWuManberCharBenchmark extends StringSearchAlgorithmsMultiCharBenchmark {

	private static final String ID = "StringSearchAlgorithms Wu-Manber";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public StringSearchAlgorithm create(List<String> pattern) {
		return new WuManber(pattern);
	}

}
