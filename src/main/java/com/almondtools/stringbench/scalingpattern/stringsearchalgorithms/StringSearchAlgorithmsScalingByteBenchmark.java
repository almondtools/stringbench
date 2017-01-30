package com.almondtools.stringbench.scalingpattern.stringsearchalgorithms;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.almondtools.stringbench.scalingpattern.ScalingPatternMatcherBenchmark;

import net.amygdalum.util.io.StreamByteProvider;
import net.amygdalum.util.io.StringByteProvider;
import net.amygdalum.stringsearchalgorithms.search.StringMatch;
import net.amygdalum.stringsearchalgorithms.search.bytes.StringSearchAlgorithm;

public abstract class StringSearchAlgorithmsScalingByteBenchmark extends ScalingPatternMatcherBenchmark {

	private StringSearchAlgorithm algorithm;

	@Override
	public void preparePatterns(Set<String> pattern) {
		this.algorithm = create(new ArrayList<>(pattern));
	}

	public abstract StringSearchAlgorithm create(List<String> pattern);

	@Override
	public List<Integer> find(String text) {
		List<StringMatch> matches = algorithm.createFinder(new StringByteProvider(text, 0, UTF_8)).findAll();
		List<Integer> indexes = new ArrayList<>(matches.size());
		for (StringMatch match : matches) {
			indexes.add((int) match.start());
		}
		return indexes;
	}

	@Override
	public List<Integer> find(File file) throws IOException {
		List<StringMatch> matches = algorithm.createFinder(new StreamByteProvider(new FileInputStream(file), UTF_8, 0, 4096, 4)).findAll();
		List<Integer> indexes = new ArrayList<>(matches.size());
		for (StringMatch match : matches) {
			indexes.add((int) match.start());
		}
		return indexes;
	}

	@Override
	public void free() {
		algorithm = null;
	}
}
