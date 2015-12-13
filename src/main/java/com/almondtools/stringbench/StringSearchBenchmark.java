package com.almondtools.stringbench;

import java.util.ArrayList;
import java.util.List;

import com.eaio.stringsearch.StringSearch;

public abstract class StringSearchBenchmark extends SinglePatternMatcherBenchmark {

	private StringSearch algorithm;
	private String[] pattern;
	private Object[] processed;

	@Override
	public void prepare(String[] pattern) {
		this.algorithm = create();
		this.pattern = pattern;
		this.processed = new Object[pattern.length];
		for (int i = 0; i < processed.length; i++) {
			processed[i] = algorithm.processString(pattern[i]);
		}
	}

	public abstract StringSearch create();

	@Override
	public List<Integer> find(int i, String text) {
		List<Integer> indexes = new ArrayList<>();
		int pos = 0;
		while (pos > -1 && pos < text.length()) {
			int result = algorithm.searchString(text, pos, pattern[i], processed[i]);
			if (result < 0) {
				break;
			}
			indexes.add(result);
			pos = result + pattern[i].length();
		}
		return indexes;
	}

}
