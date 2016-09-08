package com.almondtools.stringbench.singlepattern.incubation.stringsearch;

import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmark;
import com.eaio.stringsearch.StringSearch;

public abstract class StringSearchBenchmark extends SinglePatternMatcherBenchmark {

	private StringSearch algorithm;
	private Map<String, Object> processed;

	@Override
	public void preparePatterns(Set<String> patterns) {
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

	@Override
	public List<Integer> find(String pattern, File file) throws IOException {
		List<Integer> indexes = new ArrayList<>();
		byte[] textBytes = Files.readAllBytes(file.toPath());
		byte[] patternBytes = pattern.getBytes();
		int pos = 0;
		Object processedObject = processed.get(pattern);
		while (pos > -1 && pos < textBytes.length) {
			int result = algorithm.searchBytes(textBytes, patternBytes, processedObject);
			if (result < 0) {
				break;
			}
			indexes.add(result);
			pos = result + pattern.length();
		}
		return indexes;
	}
	
	@Override
	public void free() {
		algorithm = null;
		processed.clear();
	}

}
