package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.MS;

public class EJMSBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Maximal Shift";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		MS algorithm = MS.compile(pattern);
		return algorithm::findAll;
	}

}
