package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.FACTOR;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.BNDM;

public class EJBNDMBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Backward Non-Deterministic Dawg Matching";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return FACTOR;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		BNDM algorithm = BNDM.compile(pattern);
		return algorithm::findAll;
	}
	

}
