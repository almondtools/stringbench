package com.almondtools.stringbench.multipattern.stringsearchalgorithms;

import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.SetHorspool;
import net.amygdalum.stringsearchalgorithms.search.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCSetHorspoolRelaxedBenchmark extends StringSearchAlgorithmsMultiBenchmark {

	private static final String ID = "Strings and Chars Set Horspool (non-strict shifts)";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public StringSearchAlgorithm create(List<String> pattern) {
		return new SetHorspool(pattern, true);
	}

}
