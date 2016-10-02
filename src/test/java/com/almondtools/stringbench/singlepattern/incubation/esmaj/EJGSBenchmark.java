package com.almondtools.stringbench.singlepattern.incubation.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.SPECIAL;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbench.singlepattern.esmaj.EsmaJBenchmark;
import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.GS;

public class EJGSBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Galil-Seiferas";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return SPECIAL;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		return text -> GS.findAll(pattern, text);
	}

}
