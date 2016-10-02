package com.almondtools.stringbench.singlepattern.incubation.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbench.singlepattern.esmaj.EsmaJBenchmark;
import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.CLS;

public class EJSMNBenchmark extends EsmaJBenchmark {

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
		CLS algorithm = CLS.compile(pattern);
		return algorithm::findAll;
	}
	

}
