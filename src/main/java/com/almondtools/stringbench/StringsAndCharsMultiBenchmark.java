package com.almondtools.stringbench;

import static com.almondtools.stringsandchars.search.MatchOption.LONGEST_MATCH;
import static com.almondtools.stringsandchars.search.MatchOption.NO_OVERLAP;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.almondtools.stringsandchars.io.StringCharProvider;
import com.almondtools.stringsandchars.search.StringMatch;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

public abstract class StringsAndCharsMultiBenchmark extends MultiPatternMatcherBenchmark {

	private StringSearchAlgorithm algorithm;

	@Override
	public void prepare(Set<String> pattern) {
		this.algorithm = create(new ArrayList<>(pattern));
	}

	public abstract StringSearchAlgorithm create(List<String> pattern);

	@Override
	public List<Integer> find(String text) {
		List<StringMatch> matches = algorithm.createFinder(new StringCharProvider(text, 0), LONGEST_MATCH, NO_OVERLAP).findAll();
		List<Integer> indexes = new ArrayList<>(matches.size());
		for (StringMatch match : matches) {
			indexes.add((int) match.start());
		}
		return indexes;
	}

}
