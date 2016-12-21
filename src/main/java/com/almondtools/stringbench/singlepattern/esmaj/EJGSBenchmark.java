package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.GS;

public class EJGSBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Galil-Seiferas";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		return text -> GS.findAll(pattern, text);
	}

}
