package com.almondtools.stringbench.multipattern.stringsearchalgorithms;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.search.bytes.StringSearchAlgorithm;
import net.amygdalum.stringsearchalgorithms.search.bytes.WuManber;

@State(Scope.Thread)
public class SCWuManberByteBenchmark extends StringSearchAlgorithmsMultiByteBenchmark {

	private static final String ID = "StringSearchAlgorithms Wu-Manber";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public StringSearchAlgorithm create(List<String> pattern) {
		return new WuManber(pattern, UTF_8);
	}

}
