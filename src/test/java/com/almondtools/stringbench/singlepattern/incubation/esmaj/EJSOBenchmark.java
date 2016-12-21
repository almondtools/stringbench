package com.almondtools.stringbench.singlepattern.incubation.esmaj;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbench.singlepattern.esmaj.ESMAJBenchmark;
import com.javacodegeeks.stringsearch.SO;

public class EJSOBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Shift-Or";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		SO algorithm = SO.compile(pattern);
		return algorithm::findAll;
	}
	

}
