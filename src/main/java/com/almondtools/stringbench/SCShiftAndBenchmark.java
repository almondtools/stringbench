package com.almondtools.stringbench;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringbenchanalyzer.Family;
import com.almondtools.stringsandchars.search.ShiftAnd;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCShiftAndBenchmark extends StringsAndCharsBenchmark {

	private static final String ID = "Strings and Chars Shift-And";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public Family getFamily() {
		return PREFIX;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new ShiftAnd(pattern);
	}

}
