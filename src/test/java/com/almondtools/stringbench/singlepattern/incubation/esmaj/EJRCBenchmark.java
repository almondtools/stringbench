package com.almondtools.stringbench.singlepattern.incubation.esmaj;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbench.singlepattern.esmaj.ESMAJBenchmark;
import com.javacodegeeks.stringsearch.RC;

public class EJRCBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Reverse Colussi";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		RC algorithm = RC.compile(pattern);
		return algorithm::findAll;
	}
	

}
