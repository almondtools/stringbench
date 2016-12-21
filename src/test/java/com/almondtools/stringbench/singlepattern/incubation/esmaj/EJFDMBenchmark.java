package com.almondtools.stringbench.singlepattern.incubation.esmaj;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbench.singlepattern.esmaj.ESMAJBenchmark;
import com.javacodegeeks.stringsearch.FDM;

public class EJFDMBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Forward Dawg Matching";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		FDM algorithm = FDM.compile(pattern);
		return algorithm::findAll;
	}
	

}
