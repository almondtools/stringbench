package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.SMN;

public class EJCLSBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Colussi";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		SMN algorithm = SMN.compile(pattern);
		return algorithm::findAll;
	}
	

}
