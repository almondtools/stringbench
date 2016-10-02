package com.almondtools.stringbench.singlepattern.esmaj;

import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmark;

public abstract class ESMAJBenchmark extends SinglePatternMatcherBenchmark {

	private Map<String, Function<String, List<Integer>>> algorithm;

	@Override
	public void preparePatterns(Set<String> patterns) {
		this.algorithm = patterns.stream()
			.collect(toMap(pattern -> pattern, pattern -> createMatcher(pattern)));
	}

	public abstract Function<String, List<Integer>> createMatcher(String pattern);

	@Override
	public List<Integer> find(String pattern, String text) {
		Function<String, List<Integer>> searcher = algorithm.get(pattern);

		List<Integer> indexes = searcher.apply(text);
		return filterOverlapping(indexes, pattern.length());
	}

	@Override
	public List<Integer> find(String pattern, File file) throws IOException {
		String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
		Function<String, List<Integer>> searcher = algorithm.get(pattern);

		List<Integer> indexes = searcher.apply(text);
		return filterOverlapping(indexes, pattern.length());
	}

	private List<Integer> filterOverlapping(List<Integer> indexes, int length) {
		List<Integer> result = new ArrayList<>(indexes.size());
		int last = 0;
		for (Integer index : indexes) {
			if (index >= last) {
				result.add(index);
				last = index + length;
			}
		}
		return result;
	}

	@Override
	public void free() {
		algorithm.clear();
	}

}
