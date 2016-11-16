package com.almondtools.stringbench.singlepattern.jdk;

import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmark;

public class JavaRegexBenchmark extends SinglePatternMatcherBenchmark {

	private static final String ID = "java.util.Pattern regex search (Boyer-Moore)";

	private Map<String, Pattern> searchPattern;

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void preparePatterns(Set<String> patterns) {
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

	@Override
	public List<Integer> find(String pattern, File file) throws IOException {
		List<Integer> result = new ArrayList<>();
		String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
		Matcher matcher = searchPattern.get(pattern).matcher(text);
		while (matcher.find()) {
			result.add(matcher.start());
		}
		return result;
	}
	
	@Override
	public void free() {
		searchPattern.clear();
	}
}
