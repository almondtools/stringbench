package com.almondtools.stringbench;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.almondtools.stringbenchanalyzer.Family;

public class JavaRegexBenchmark extends SinglePatternMatcherBenchmark {

	private static final String ID = "java.util.Pattern regex search (boyer-moore)";

	private Map<String, Pattern> searchPattern;

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return SUFFIX;
	}

	@Override
	public void prepare(Set<String> patterns) {
		searchPattern = patterns.stream()
			.collect(toMap(pattern -> pattern, pattern -> Pattern.compile(Pattern.quote(pattern))));
	}

	@Override
	public List<Integer> find(String pattern, String text) {
		List<Integer> result = new ArrayList<>();
		Matcher matcher = searchPattern.get(pattern).matcher(text);
		while (matcher.find()) {
			result.add(matcher.start());
		}
		return result;
	}

}
