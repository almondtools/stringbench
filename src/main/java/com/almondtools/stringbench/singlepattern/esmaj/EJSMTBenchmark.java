package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.SMT;

public class EJSMTBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Smith";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		SMT algorithm = SMT.compile(pattern);
		return algorithm::findAll;
	}
	

}
