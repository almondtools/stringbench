package com.almondtools.stringbench.singlepattern.incubation.esmaj;

import java.util.List;
import java.util.function.Function;

import com.almondtools.stringbench.singlepattern.esmaj.ESMAJBenchmark;
import com.javacodegeeks.stringsearch.ZT;

public class EJZTBenchmark extends ESMAJBenchmark {

	private static final String ID = "EsmaJ Zhu-Takaoka";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Function<String, List<Integer>> createMatcher(String pattern) {
		ZT algorithm = ZT.compile(pattern);
		return algorithm::findAll;
	}
	

}
