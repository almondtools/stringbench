package com.almondtools.stringbench;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegexMultiBenchmark extends MultiPatternMatcherBenchmark {

	private static final String JAVA_REGEX = "java.util.Pattern regex search for multiple strings (regex)";

	private Pattern searchPattern;
	
	@Override
	public String getId() {
		return JAVA_REGEX;
	}

	@Override
	public void prepare(String[] pattern) {
		searchPattern = Pattern.compile(pattern(pattern));
	}

	private String pattern(String[] pattern) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < pattern.length; i++) {
			buffer.append('|');
			buffer.append(Pattern.quote(pattern[i]));
		}
		return buffer.substring(1);
	}

	@Override
	public List<Integer> find(String text) {
		List<Integer> result = new ArrayList<Integer>();
		Matcher matcher = searchPattern.matcher(text);
		while (matcher.find()) {
			result.add(matcher.start());
		}
		return result;
	}

}
