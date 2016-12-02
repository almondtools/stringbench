package com.almondtools.stringbench.scalingpattern.stringsearchalgorithms;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.bytes.AhoCorasick;
import net.amygdalum.stringsearchalgorithms.search.bytes.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCAhoCorasickByteBenchmark extends StringSearchAlgorithmsScalingByteBenchmark {

	private static final String ID = "StringSearchAlgorithms Aho-Corasick";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearchAlgorithm create(List<String> pattern) {
		return new AhoCorasick(pattern, UTF_8);
	}

}
