package com.almondtools.stringbench.multipattern.stringsearchalgorithms;

import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.chars.SetHorspool;
import net.amygdalum.stringsearchalgorithms.search.chars.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCSetHorspoolRelaxedCharBenchmark extends StringSearchAlgorithmsMultiCharBenchmark {

	private static final String ID = "StringSearchAlgorithms Set Horspool (non-strict shifts)";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public StringSearchAlgorithm create(List<String> pattern) {
		return new SetHorspool(pattern, true);
	}

}
