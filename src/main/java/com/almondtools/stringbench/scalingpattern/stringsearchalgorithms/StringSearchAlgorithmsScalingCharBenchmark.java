package com.almondtools.stringbench.scalingpattern.stringsearchalgorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.almondtools.stringbench.scalingpattern.ScalingPatternMatcherBenchmark;

import net.amygdalum.util.io.ReaderCharProvider;
import net.amygdalum.util.io.StringCharProvider;
import net.amygdalum.stringsearchalgorithms.search.StringMatch;
import net.amygdalum.stringsearchalgorithms.search.chars.StringSearchAlgorithm;

public abstract class StringSearchAlgorithmsScalingCharBenchmark extends ScalingPatternMatcherBenchmark {

	private StringSearchAlgorithm algorithm;

	@Override
	public void preparePatterns(Set<String> pattern) {
		this.algorithm = create(new ArrayList<>(pattern));
	}

	public abstract StringSearchAlgorithm create(List<String> pattern);

	@Override
	public List<Integer> find(String text) {
		List<StringMatch> matches = algorithm.createFinder(new StringCharProvider(text, 0)).findAll();
		List<Integer> indexes = new ArrayList<>(matches.size());
		for (StringMatch match : matches) {
			indexes.add((int) match.start());
		}
		return indexes;
	}

	@Override
	public List<Integer> find(File file) throws IOException {
		Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
		List<StringMatch> matches = algorithm.createFinder(new ReaderCharProvider(reader, 0, 4096, 4)).findAll();
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
