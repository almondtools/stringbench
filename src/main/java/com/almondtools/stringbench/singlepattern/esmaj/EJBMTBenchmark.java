package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.BMT;

public class EJBMTBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Tuned Boyer-Moore";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		BMT algorithm = BMT.compile(pattern);
		return algorithm::findAll;
	}
	

}
