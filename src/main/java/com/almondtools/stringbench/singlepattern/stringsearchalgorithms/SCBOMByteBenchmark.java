package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.bytes.BOM;
import net.amygdalum.stringsearchalgorithms.search.bytes.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCBOMByteBenchmark extends StringSearchAlgorithmsByteBenchmark {

	private static final String ID = "StringSearchAlgorithms BOM";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(String pattern) {
		return new BOM(pattern, UTF_8);
	}

}
