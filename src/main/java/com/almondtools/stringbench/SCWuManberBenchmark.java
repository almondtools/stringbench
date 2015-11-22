package com.almondtools.stringbench;

import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringsandchars.search.StringSearchAlgorithm;
import com.almondtools.stringsandchars.search.WuManber;

@State(Scope.Thread)
public class SCWuManberBenchmark extends StringsAndCharsMultiBenchmark {

	private static final String ID = "Strings and Chars Wu-Manber";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(List<String> pattern) {
		return new WuManber(pattern);
	}

}
