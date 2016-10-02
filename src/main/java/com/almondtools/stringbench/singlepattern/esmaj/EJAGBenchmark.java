package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.AG;

public class EJAGBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Apostolico-Giancarlo";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return SUFFIX;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		AG algorithm = AG.compile(pattern);
		return algorithm::findAll;
	}
	

}
