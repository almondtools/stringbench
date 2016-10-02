package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.SPECIAL;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.SMOA;

public class EJSMOABenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ String Matching on Ordered Alphabets";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return SPECIAL;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		return text -> SMOA.findAll(pattern, text);
	}

}
