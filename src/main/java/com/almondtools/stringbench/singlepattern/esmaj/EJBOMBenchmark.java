package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.FACTOR;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.BOM;

public class EJBOMBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Backward Oracle Matching";

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
		BOM algorithm = BOM.compile(pattern);
		return algorithm::findAll;
	}
	

}
