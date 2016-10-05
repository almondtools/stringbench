package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.GG;

public class EJGGBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Galil-Giancarlo";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return PREFIX;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		GG algorithm = GG.compile(pattern);
		return algorithm::findAll;
	}
	

}
