package com.almondtools.stringbench.multipattern.jdk;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;

public class JavaIndexOfMultiBenchmark extends MultiPatternMatcherBenchmark {

	private static final String ID = "String.indexOf (naive)";

	private String[] searchPattern;

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void preparePatterns(Set<String> pattern) {
		searchPattern = pattern.stream().toArray(String[]::new);
	}

	@Override
	public List<Integer> find(String text) {
		List<Integer> result = new ArrayList<>();
		find(text, result);
		return result;
	}

	@Override
	public List<Integer> find(File file) throws IOException {
		List<Integer> result = new ArrayList<>();
		String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
		find(text, result);
		return result;
	}

	private void find(String text, List<Integer> result) {
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
					next[j] = text.indexOf(searchPattern[j], pos);
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
	}

	@Override
	public void free() {
		searchPattern = null;
	}
}
