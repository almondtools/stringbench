package com.almondtools.stringbench.singlepattern.incubation.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbench.singlepattern.esmaj.EsmaJBenchmark;
import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.SO;

public class EJSOBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Shift-Or";

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
		SO algorithm = SO.compile(pattern);
		return algorithm::findAll;
	}
	

}
