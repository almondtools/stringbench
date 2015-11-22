package com.almondtools.stringbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringsandchars.search.StringSearchAlgorithm;
import com.almondtools.stringsandchars.search.Sunday;

@State(Scope.Thread)
public class SCSundayBenchmark extends StringsAndCharsBenchmark {

	private static final String ID = "Strings and Chars Sunday";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new Sunday(pattern);
	}

}
