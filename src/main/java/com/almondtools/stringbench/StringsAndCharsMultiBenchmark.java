package com.almondtools.stringbench;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.almondtools.stringsandchars.io.StringCharProvider;
import com.almondtools.stringsandchars.search.StringMatch;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

public abstract class StringsAndCharsMultiBenchmark extends MultiPatternMatcherBenchmark {

	private StringSearchAlgorithm[] algorithm;

	@Override
	public void prepare(String[] pattern) {
		this.algorithm = new StringSearchAlgorithm[pattern.length];
		for (int i = 0; i < algorithm.length; i++) {
			algorithm[i] = create(asList(pattern));
		}
	}

	public abstract StringSearchAlgorithm create(List<String> pattern);

	@Override
	public List<Integer> find(int i, String text) {
		List<StringMatch> matches = algorithm[i].createFinder(new StringCharProvider(text, 0)).findAll();
		List<Integer> indexes = new ArrayList<Integer>(matches.size());
		for (StringMatch match : matches) {
			indexes.add((int) match.start());
		}
		return indexes;
	}

}
