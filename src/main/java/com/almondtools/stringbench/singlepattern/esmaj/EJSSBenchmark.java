package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.SS;

public class EJSSBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Skip Search";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		SS algorithm = SS.compile(pattern);
		return algorithm::findAll;
	}

}
