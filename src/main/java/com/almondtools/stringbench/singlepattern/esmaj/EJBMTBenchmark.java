package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.BMT;

public class EJBMTBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Tuned Boyer-Moore";

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
		BMT algorithm = BMT.compile(pattern);
		return algorithm::findAll;
	}
	

}
