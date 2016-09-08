package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import static com.almondtools.stringbenchanalyzer.Family.FACTOR;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringbenchanalyzer.Family;
import net.amygdalum.stringsearchalgorithms.search.BNDM;
import net.amygdalum.stringsearchalgorithms.search.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCBNDMBenchmark extends StringSearchAlgorithmsBenchmark {

	private static final String ID = "Strings and Chars BNDM";

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
		return new BNDM(pattern);
	}

}
