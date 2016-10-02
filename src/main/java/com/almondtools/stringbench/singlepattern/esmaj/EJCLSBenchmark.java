package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.SMN;

public class EJCLSBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Simon";

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
		SMN algorithm = SMN.compile(pattern);
		return algorithm::findAll;
	}
	

}
