package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.TBM;

public class EJTBMBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Turbo-BM";

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
		TBM algorithm = TBM.compile(pattern);
		return algorithm::findAll;
	}
	

}
