package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.BR;

public class EJBRBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Berry-Ravindran";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		BR algorithm = BR.compile(pattern);
		return algorithm::findAll;
	}
	

}
