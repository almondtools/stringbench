package com.almondtools.stringbench.singlepattern.esmaj;

import java.util.List;
import java.util.function.Function;

import com.javacodegeeks.stringsearch.KPMSS;

public class EJKPMSSBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ KMP Skip Search";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		KPMSS algorithm = KPMSS.compile(pattern);
		return algorithm::findAll;
	}

}
