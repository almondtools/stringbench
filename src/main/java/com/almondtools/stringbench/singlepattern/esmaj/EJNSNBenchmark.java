package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.NSN;

public class EJNSNBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Not So Naive";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		return text -> NSN.findAll(pattern, text);
	}
	

}
