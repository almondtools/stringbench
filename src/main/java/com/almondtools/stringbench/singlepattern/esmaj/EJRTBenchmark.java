package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.RT;

public class EJRTBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Raita";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		RT algorithm = RT.compile(pattern);
		return algorithm::findAll;
	}
	

}
