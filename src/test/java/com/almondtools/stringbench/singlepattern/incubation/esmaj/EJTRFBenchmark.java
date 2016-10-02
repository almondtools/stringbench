package com.almondtools.stringbench.singlepattern.incubation.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.FACTOR;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbench.singlepattern.esmaj.EsmaJBenchmark;
import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.TRF;

public class EJTRFBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Turbo Reverse Factor";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return FACTOR;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		TRF algorithm = TRF.compile(pattern);
		return algorithm::findAll;
	}
	

}
