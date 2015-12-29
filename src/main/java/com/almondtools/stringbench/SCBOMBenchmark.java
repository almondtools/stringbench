package com.almondtools.stringbench;

import static com.almondtools.stringbenchanalyzer.Family.FACTOR;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringbenchanalyzer.Family;
import com.almondtools.stringsandchars.search.BOM;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCBOMBenchmark extends StringsAndCharsBenchmark {

	private static final String ID = "Strings and Chars BOM";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public Family getFamily() {
		return FACTOR;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new BOM(pattern);
	}

}
