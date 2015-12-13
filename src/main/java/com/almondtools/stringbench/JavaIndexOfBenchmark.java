package com.almondtools.stringbench;

import java.util.ArrayList;
import java.util.List;

public class JavaIndexOfBenchmark extends SinglePatternMatcherBenchmark {

	private static final String JAVA_REGEX = "String.indexOf (naive)";

	private String[] searchPattern;

	@Override
	public String getId() {
		return JAVA_REGEX;
	}

	@Override
	public void prepare(String[] pattern) {
		searchPattern = pattern;
	}

	@Override
	public List<Integer> find(int i, String text) {
		List<Integer> result = new ArrayList<>();
		int pos = 0;
		while (pos > -1 && pos < text.length()) {
			int match = text.indexOf(searchPattern[i], pos);
			if (match < 0) {
				pos = match;
			} else {
				result.add(match);
				pos = match + searchPattern[i].length();
			}
		}
		return result;
	}

}
