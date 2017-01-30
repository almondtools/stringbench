package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toMap;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.LONGEST_MATCH;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.NON_OVERLAP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmark;

import net.amygdalum.util.io.StreamByteProvider;
import net.amygdalum.util.io.StringByteProvider;
import net.amygdalum.stringsearchalgorithms.search.StringMatch;
import net.amygdalum.stringsearchalgorithms.search.bytes.StringSearchAlgorithm;


public abstract class StringSearchAlgorithmsByteBenchmark extends SinglePatternMatcherBenchmark {

	private Map<String, StringSearchAlgorithm> algorithm;

	@Override
	public void preparePatterns(Set<String> patterns) {
		this.algorithm = patterns.stream()
			.collect(toMap(pattern -> pattern, pattern -> create(pattern)));
	}

	public abstract StringSearchAlgorithm create(String pattern);

	@Override
	public List<Integer> find(String pattern, String text) {
		List<StringMatch> matches = algorithm.get(pattern).createFinder(new StringByteProvider(text, 0, UTF_8), LONGEST_MATCH, NON_OVERLAP).findAll();
		List<Integer> indexes = new ArrayList<>(matches.size());
		for (StringMatch match : matches) {
			indexes.add((int) match.start());
		}
		return indexes;
	}

	@Override
	public List<Integer> find(String pattern, File file) throws IOException {
		List<StringMatch> matches = algorithm.get(pattern).createFinder(new StreamByteProvider(new FileInputStream(file), UTF_8, 0, 4096, 4), LONGEST_MATCH, NON_OVERLAP).findAll();
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
