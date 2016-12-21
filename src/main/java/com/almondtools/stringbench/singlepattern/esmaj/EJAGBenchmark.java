package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.AG;

public class EJAGBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Apostolico-Giancarlo";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		AG algorithm = AG.compile(pattern);
		return algorithm::findAll;
	}
	

}
