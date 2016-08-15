package com.almondtools.stringbench;

import static com.almondtools.stringbenchanalyzer.Family.NAIVE;

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
	public void prepareText(String text) {
	}

	@Override
	public List<Integer> find(String pattern, String text) {
		List<Integer> result = new ArrayList<>();
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
		return result;
	}

}
