package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.BOM;

public class EJBOMBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Backward Oracle Matching";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		BOM algorithm = BOM.compile(pattern);
		return algorithm::findAll;
	}
	

}
