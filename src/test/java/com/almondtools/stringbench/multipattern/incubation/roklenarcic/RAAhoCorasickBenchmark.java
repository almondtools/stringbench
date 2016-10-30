package com.almondtools.stringbench.multipattern.incubation.roklenarcic;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;
import com.almondtools.stringbenchanalyzer.Family;
import com.roklenarcic.util.strings.LongestMatchSet;
import com.roklenarcic.util.strings.SetMatchListener;

public class RAAhoCorasickBenchmark extends MultiPatternMatcherBenchmark {

	private static final String ID = "RokLenarcics Aho-Corasick";

	private LongestMatchSet set;

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return PREFIX;
	}

	@Override
	public void preparePatterns(Set<String> patterns) {
		set = new LongestMatchSet(patterns, true);
	}

	@Override
	public List<Integer> find(String text) {
		List<Integer> result = new ArrayList<>();
		set.match(text, new SetMatchListener() {
			
			@Override
			public boolean match(String haystack, int startPosition, int endPosition) {
				result.add(startPosition);
				return true;
			}
		});
		return result;
	}

	@Override
	public List<Integer> find(File file) throws IOException {
		List<Integer> result = new ArrayList<>();
		String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

		set.match(text, new SetMatchListener() {
			
			@Override
			public boolean match(String haystack, int startPosition, int endPosition) {
				result.add(startPosition);
				return true;
			}
		});
		return result;
	}

	@Override
	public void free() {
		set = null;
	}
}
