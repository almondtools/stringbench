package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringbenchanalyzer.Family;
import net.amygdalum.stringsearchalgorithms.search.StringSearchAlgorithm;
import net.amygdalum.stringsearchalgorithms.search.Sunday;

@State(Scope.Thread)
public class SCSundayBenchmark extends StringSearchAlgorithmsBenchmark {

	private static final String ID = "Strings and Chars Sunday";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public Family getFamily() {
		return SUFFIX;
	}

	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new Sunday(pattern);
	}

}
