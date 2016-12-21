package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.BM;

public class EJBMBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Boyer-Moore";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		BM algorithm = BM.compile(pattern);
		return algorithm::findAll;
	}
	

}
