package com.almondtools.stringbench;

import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.byteseek.io.reader.FileReader;
import net.byteseek.io.reader.WindowReader;
import net.byteseek.matcher.sequence.SequenceMatcher;
import net.byteseek.searcher.ForwardSearchIterator;
import net.byteseek.searcher.SearchResult;
import net.byteseek.searcher.Searcher;

public abstract class ByteSeekBenchmark extends SinglePatternMatcherBenchmark {

	private Map<String, Searcher<SequenceMatcher>> algorithm;

	@Override
	public void preparePatterns(Set<String> patterns) {
		this.algorithm = patterns.stream()
			.collect(toMap(pattern -> pattern, pattern -> preparePattern(pattern)));
	}

	private Searcher<SequenceMatcher> preparePattern(String pattern) {
		Searcher<SequenceMatcher> searcher = create(pattern);
		searcher.prepareForwards();
		return searcher;
	}

	public abstract Searcher<SequenceMatcher> create(String pattern);

	@Override
	public List<Integer> find(String pattern, String text) {
		Searcher<SequenceMatcher> searcher = algorithm.get(pattern);
		byte[] stringBytes = text.getBytes();

		ForwardSearchIterator<SequenceMatcher> searchIterator = new ForwardSearchIterator<SequenceMatcher>(searcher, stringBytes, 0);

		List<Integer> indexes = new ArrayList<>();

		long lastPosition = -1;
		while (searchIterator.hasNext()) {
			List<SearchResult<SequenceMatcher>> results = searchIterator.next();
			for (SearchResult<SequenceMatcher> result : results) {
				long pos = result.getMatchPosition();
				if (pos >= lastPosition) {
					indexes.add((int) pos);
					lastPosition = pos + result.getMatchingObject().length();
				}
			}
		}
		return indexes;
	}

	@Override
	public List<Integer> find(String pattern, File file) throws IOException {
		Searcher<SequenceMatcher> searcher = algorithm.get(pattern);
		WindowReader reader = new FileReader(file);
		
		ForwardSearchIterator<SequenceMatcher> searchIterator = new ForwardSearchIterator<SequenceMatcher>(searcher, reader);

		List<Integer> indexes = new ArrayList<>();

		long lastPosition = -1;
		while (searchIterator.hasNext()) {
			List<SearchResult<SequenceMatcher>> results = searchIterator.next();
			for (SearchResult<SequenceMatcher> result : results) {
				long pos = result.getMatchPosition();
				if (pos >= lastPosition) {
					indexes.add((int) pos);
					lastPosition = pos + result.getMatchingObject().length();
				}
			}
		}
		return indexes;
	}
}
