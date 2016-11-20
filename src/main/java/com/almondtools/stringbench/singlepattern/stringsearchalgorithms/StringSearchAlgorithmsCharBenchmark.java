package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import static java.util.stream.Collectors.toMap;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.LONGEST_MATCH;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.NON_OVERLAP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmark;

import net.amygdalum.stringsearchalgorithms.io.ReaderCharProvider;
import net.amygdalum.stringsearchalgorithms.io.StringCharProvider;
import net.amygdalum.stringsearchalgorithms.search.StringMatch;
import net.amygdalum.stringsearchalgorithms.search.chars.StringSearchAlgorithm;


public abstract class StringSearchAlgorithmsCharBenchmark extends SinglePatternMatcherBenchmark {

	private Map<String, StringSearchAlgorithm> algorithm;

	@Override
	public void preparePatterns(Set<String> patterns) {
		this.algorithm = patterns.stream()
			.collect(toMap(pattern -> pattern, pattern -> create(pattern)));
	}

	public abstract StringSearchAlgorithm create(String pattern);

	@Override
	public List<Integer> find(String pattern, String text) {
		List<StringMatch> matches = algorithm.get(pattern).createFinder(new StringCharProvider(text, 0), LONGEST_MATCH, NON_OVERLAP).findAll();
		List<Integer> indexes = new ArrayList<>(matches.size());
		for (StringMatch match : matches) {
			indexes.add((int) match.start());
		}
		return indexes;
	}

	@Override
	public List<Integer> find(String pattern, File file) throws IOException {
		Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
		List<StringMatch> matches = algorithm.get(pattern).createFinder(new ReaderCharProvider(reader, 0, 4096, 4), LONGEST_MATCH, NON_OVERLAP).findAll();
		List<Integer> indexes = new ArrayList<>(matches.size());
		for (StringMatch match : matches) {
			indexes.add((int) match.start());
		}
		return indexes;
	}
	
	@Override
	public void free() {
		algorithm.clear();
	}

}
