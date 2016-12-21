package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.AC;

public class EJACBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Apostolico-Crochemore";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		AC algorithm = AC.compile(pattern);
		return algorithm::findAll;
	}
	

}
