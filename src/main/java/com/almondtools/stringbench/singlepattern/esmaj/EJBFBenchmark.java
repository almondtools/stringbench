package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.NAIVE;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.BF;

public class EJBFBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Brute Force";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return NAIVE;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		return text -> BF.findAll(pattern, text);
	}
	

}
