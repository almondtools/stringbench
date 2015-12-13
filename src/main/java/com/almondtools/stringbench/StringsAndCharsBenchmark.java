package com.almondtools.stringbench;

import java.util.ArrayList;
import java.util.List;

import com.almondtools.stringsandchars.io.StringCharProvider;
import com.almondtools.stringsandchars.search.StringMatch;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

public abstract class StringsAndCharsBenchmark extends SinglePatternMatcherBenchmark {

	private StringSearchAlgorithm[] algorithm;

	@Override
	public void prepare(String[] pattern) {
		this.algorithm = new StringSearchAlgorithm[pattern.length];
		for (int i = 0; i < algorithm.length; i++) {
			algorithm[i] = create(pattern[i]);
		}
	}

	public abstract StringSearchAlgorithm create(String pattern);

	@Override
	public List<Integer> find(int i, String text) {
		List<StringMatch> matches = algorithm[i].createFinder(new StringCharProvider(text, 0)).findAll();
		List<Integer> indexes = new ArrayList<>(matches.size());
		for (StringMatch match : matches) {
			indexes.add((int) match.start());
		}
		return indexes;
	}

}
