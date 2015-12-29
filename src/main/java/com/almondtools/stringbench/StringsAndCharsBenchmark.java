package com.almondtools.stringbench;

import static com.almondtools.stringsandchars.search.MatchOption.LONGEST_MATCH;
import static com.almondtools.stringsandchars.search.MatchOption.NO_OVERLAP;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.almondtools.stringsandchars.io.StringCharProvider;
import com.almondtools.stringsandchars.search.StringMatch;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

public abstract class StringsAndCharsBenchmark extends SinglePatternMatcherBenchmark {

	private Map<String, StringSearchAlgorithm> algorithm;

	@Override
	public void prepare(Set<String> patterns) {
		this.algorithm = patterns.stream()
			.collect(toMap(pattern -> pattern, pattern ->create(pattern)));
	}

	public abstract StringSearchAlgorithm create(String pattern);

	@Override
	public List<Integer> find(String pattern, String text) {
		List<StringMatch> matches = algorithm.get(pattern).createFinder(new StringCharProvider(text, 0), LONGEST_MATCH, NO_OVERLAP).findAll();
		List<Integer> indexes = new ArrayList<>(matches.size());
		for (StringMatch match : matches) {
			indexes.add((int) match.start());
		}
		return indexes;
	}

}
