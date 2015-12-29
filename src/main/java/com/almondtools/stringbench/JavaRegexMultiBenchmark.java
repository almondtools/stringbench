package com.almondtools.stringbench;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.almondtools.stringbenchanalyzer.Family;

public class JavaRegexMultiBenchmark extends MultiPatternMatcherBenchmark {

	private static final String ID = "java.util.Pattern regex search for multiple strings (regex)";

	private Pattern searchPattern;

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return SUFFIX;
	}

	@Override
	public void prepare(Set<String> pattern) {
		searchPattern = Pattern.compile(pattern(pattern));
	}

	private String pattern(Set<String> pattern) {
		String[] sortedpatterns = pattern.toArray(new String[0]);
		Arrays.sort(sortedpatterns, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		});
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < sortedpatterns.length; i++) {
			buffer.append('|');
			buffer.append(Pattern.quote(sortedpatterns[i]));
		}
		return buffer.substring(1);
	}

	@Override
	public List<Integer> find(String text) {
		List<Integer> result = new ArrayList<>();
		Matcher matcher = searchPattern.matcher(text);
		while (matcher.find()) {
			result.add(matcher.start());
		}
		return result;
	}

}
