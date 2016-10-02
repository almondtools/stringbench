package com.almondtools.stringbench.singlepattern.esmaj;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbenchanalyzer.Family;
import com.javacodegeeks.stringsearch.ZT;

public class EJZTBenchmark extends EsmaJBenchmark {

	private static final String ID = "EsmaJ Zhu-Takaoka";

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
		ZT algorithm = ZT.compile(pattern);
		return algorithm::findAll;
	}
	

}
