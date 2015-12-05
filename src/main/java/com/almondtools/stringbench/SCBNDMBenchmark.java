package com.almondtools.stringbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringsandchars.search.BNDM;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCBNDMBenchmark extends StringsAndCharsBenchmark {

	private static final String ID = "Strings and Chars BNDM";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new BNDM(pattern);
	}

}
