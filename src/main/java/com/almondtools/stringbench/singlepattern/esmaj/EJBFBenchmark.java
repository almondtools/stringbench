package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.BF;

public class EJBFBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Brute Force";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		return text -> BF.findAll(pattern, text);
	}
	

}
