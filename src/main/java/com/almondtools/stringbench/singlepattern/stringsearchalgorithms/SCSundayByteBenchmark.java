package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.bytes.StringSearchAlgorithm;
import net.amygdalum.stringsearchalgorithms.search.bytes.Sunday;

@State(Scope.Thread)
public class SCSundayByteBenchmark extends StringSearchAlgorithmsByteBenchmark {

	private static final String ID = "StringSearchAlgorithms Sunday";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new Sunday(pattern, UTF_8);
	}

}
