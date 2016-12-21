package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.TBM;

public class EJTBMBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Turbo-BM";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		TBM algorithm = TBM.compile(pattern);
		return algorithm::findAll;
	}
	

}
