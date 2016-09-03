package com.almondtools.stringbench;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringbenchanalyzer.Family;
import net.amygdalum.stringsearchalgorithms.search.KnuthMorrisPratt;
import net.amygdalum.stringsearchalgorithms.search.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCKnuthMorrisPrattBenchmark extends StringsAndCharsBenchmark {

	private static final String ID = "Strings and Chars Knuth-Morris-Pratt";

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
		return new KnuthMorrisPratt(pattern);
	}

}
