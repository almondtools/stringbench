package com.almondtools.stringbench.singlepattern;

import static com.almondtools.stringbenchanalyzer.Family.NAIVE;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.almondtools.stringbenchanalyzer.Family;

public class JavaIndexOfBenchmark extends SinglePatternMatcherBenchmark {

	private static final String ID = "String.indexOf (naive)";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public Family getFamily() {
		return NAIVE;
	}

	@Override
	public void preparePatterns(Set<String> patterns) {
	}

	@Override
	public List<Integer> find(String pattern, String text) {
		List<Integer> result = new ArrayList<>();
		find(pattern, text, result);
		return result;
	}

	@Override
	public List<Integer> find(String pattern, File file) throws IOException {
		List<Integer> result = new ArrayList<>();
		String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
		find(pattern, text, result);
		return result;
	}

	private void find(String pattern, String text, List<Integer> result) {
		int pos = 0;
		while (pos > -1 && pos < text.length()) {
			int match = text.indexOf(pattern, pos);
			if (match < 0) {
				pos = match;
			} else {
				result.add(match);
				pos = match + pattern.length();
			}
		}
	}

}
