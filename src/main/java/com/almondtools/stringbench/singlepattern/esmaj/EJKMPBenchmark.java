package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.KMP;

public class EJKMPBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Morris-Pratt";

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
		KMP algorithm = KMP.compile(pattern);
		return algorithm::findAll;
	}
	

}
