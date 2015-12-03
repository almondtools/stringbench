package com.almondtools.stringbench;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaIndexOfMultiBenchmark extends MultiPatternMatcherBenchmark {

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
	public List<Integer> find(String text) {
		List<Integer> result = new ArrayList<Integer>();
		int next[] = new int[searchPattern.length]; 
		Arrays.fill(next, -1);
		int pos = 0;
		while (pos > -1 && pos < text.length()) {
			int match = Integer.MAX_VALUE;
			int len = 0;
			for (int j = 0; j < next.length; j++) {
				if (next[j] == Integer.MAX_VALUE) {
					continue;
				} else if (next[j] < pos) {
					next[j] =  text.indexOf(searchPattern[j], pos);
					if (next[j] < 0) {
						next[j] = Integer.MAX_VALUE;
					}
				}
				if (next[j] < match) {
					match = next[j];
					len = searchPattern[j].length();
				} else if (next[j] == match && len < searchPattern[j].length()) {
					len = searchPattern[j].length();
				}
			}
			if (match == Integer.MAX_VALUE) {
				pos = -1;
			} else {
				result.add(match);
				pos = match + len;
			}
		}
		return result;
	}

}
