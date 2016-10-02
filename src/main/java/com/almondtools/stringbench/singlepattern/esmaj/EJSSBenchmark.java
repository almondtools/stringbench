package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.SPECIAL;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.SS;

public class EJSSBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Skip Search";

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
		SS algorithm = SS.compile(pattern);
		return algorithm::findAll;
	}

}
