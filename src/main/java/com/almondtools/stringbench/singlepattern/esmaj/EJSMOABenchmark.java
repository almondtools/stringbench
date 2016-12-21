package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.SMOA;

public class EJSMOABenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ String Matching on Ordered Alphabets";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		return text -> SMOA.findAll(pattern, text);
	}

}
