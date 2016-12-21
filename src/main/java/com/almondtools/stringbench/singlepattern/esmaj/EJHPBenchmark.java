package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.HP;

public class EJHPBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Horspool";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		HP algorithm = HP.compile(pattern);
		return algorithm::findAll;
	}
	

}
