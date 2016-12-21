package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.TW;

public class EJTWBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Two Way";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		TW algorithm = TW.compile(pattern);
		return algorithm::findAll;
	}

}
