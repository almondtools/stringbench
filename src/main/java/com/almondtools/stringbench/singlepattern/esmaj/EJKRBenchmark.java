package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.HASHING;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.KR;

public class EJKRBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Karp-Rabin";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return HASHING;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		return text -> KR.findAll(pattern, text);
	}
	

}
