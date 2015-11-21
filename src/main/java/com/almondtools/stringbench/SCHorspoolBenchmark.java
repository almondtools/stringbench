package com.almondtools.stringbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringsandchars.search.Horspool;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCHorspoolBenchmark extends StringsAndCharsBenchmark {

	private static final String ID = "Strings and Chars Horspool";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new Horspool(pattern);
	}

}
