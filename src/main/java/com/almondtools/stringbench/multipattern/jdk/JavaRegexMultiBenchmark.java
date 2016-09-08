package com.almondtools.stringbench.multipattern.jdk;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;
import com.almondtools.stringbenchanalyzer.Family;

public class JavaRegexMultiBenchmark extends MultiPatternMatcherBenchmark {

	private static final String ID = "java.util.Pattern regex search for multiple strings (Nondeterministic Regular Expression Search)";

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
	public void preparePatterns(Set<String> pattern) {
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

	@Override
	public List<Integer> find(File file) throws IOException {
		List<Integer> result = new ArrayList<>();
		String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
		Matcher matcher = searchPattern.matcher(text);
		while (matcher.find()) {
			result.add(matcher.start());
		}
		return result;
	}

	@Override
	public void free() {
		searchPattern = null;
	}
}
