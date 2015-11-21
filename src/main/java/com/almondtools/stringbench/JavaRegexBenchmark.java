package com.almondtools.stringbench;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegexBenchmark extends MatcherBenchmark {

	private static final String JAVA_REGEX = "java.util.Pattern regex search (boyer-moore)";

	private Pattern[] searchPattern;
	
	@Override
	public String getId() {
		return JAVA_REGEX;
	}

	@Override
	public void prepare(String[] pattern) {
		searchPattern = new Pattern[pattern.length];
		for (int i = 0; i < pattern.length; i++) {
			searchPattern[i] = Pattern.compile(Pattern.quote(pattern[i]));
		}
	}

	@Override
	public List<Integer> find(int i, String text) {
		List<Integer> result = new ArrayList<Integer>();
		Matcher matcher = searchPattern[i].matcher(text);
		while (matcher.find()) {
			result.add(matcher.start());
		}
		return result;
	}

}
