package com.almondtools.stringbench;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.eaio.stringsearch.StringSearch;

public abstract class StringSearchBenchmark extends SinglePatternMatcherBenchmark {

	private StringSearch algorithm;
	private Map<String, Object> processed;

	@Override
	public void prepare(Set<String> patterns) {
		this.algorithm = create();
		this.processed = patterns.stream()
			.filter(pattern -> algorithm.processString(pattern) != null)
			.collect(toMap(pattern -> pattern, pattern -> algorithm.processString(pattern)));
	}

	public abstract StringSearch create();

	@Override
	public List<Integer> find(String pattern, String text) {
		List<Integer> indexes = new ArrayList<>();
		int pos = 0;
		Object processedObject = processed.get(pattern);
		while (pos > -1 && pos < text.length()) {
			int result = algorithm.searchString(text, pos, pattern, processedObject);
			if (result < 0) {
				break;
			}
			indexes.add(result);
			pos = result + pattern.length();
		}
		return indexes;
	}

}
