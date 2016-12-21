package com.almondtools.stringbench.singlepattern.incubation.esmaj;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbench.singlepattern.esmaj.ESMAJBenchmark;
import com.javacodegeeks.stringsearch.BNDM;

public class EJBNDMBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Backward Non-Deterministic Dawg Matching";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		BNDM algorithm = BNDM.compile(pattern);
		return algorithm::findAll;
	}
	

}
