package com.almondtools.stringbench;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringbenchanalyzer.Family;
import com.almondtools.stringsandchars.search.AhoCorasick;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCAhoCorasickBenchmark extends StringsAndCharsMultiBenchmark {

	private static final String ID = "Strings and Chars Aho-Corasick";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public Family getFamily() {
		return PREFIX;
	}
	
	@Override
	public StringSearchAlgorithm create(List<String> pattern) {
		return new AhoCorasick(pattern);
	}

}
