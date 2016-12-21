package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.KR;

public class EJKRBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Karp-Rabin";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		return text -> KR.findAll(pattern, text);
	}
	

}
