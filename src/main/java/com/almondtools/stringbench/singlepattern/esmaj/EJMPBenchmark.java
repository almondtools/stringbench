package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.MP;

public class EJMPBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Morris-Pratt";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		MP algorithm = MP.compile(pattern);
		return algorithm::findAll;
	}
	

}
