package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.KMP;

public class EJKMPBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Knuth-Morris-Pratt";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		KMP algorithm = KMP.compile(pattern);
		return algorithm::findAll;
	}
	

}
