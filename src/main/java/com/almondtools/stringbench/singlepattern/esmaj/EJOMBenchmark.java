package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.OM;

public class EJOMBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Optimal Mismatch";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		return text -> OM.findAll(pattern, text);
	}

}
