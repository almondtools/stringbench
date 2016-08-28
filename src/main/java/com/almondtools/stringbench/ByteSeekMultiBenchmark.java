package com.almondtools.stringbench;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.byteseek.io.reader.FileReader;
import net.byteseek.io.reader.WindowReader;
import net.byteseek.matcher.multisequence.ListMultiSequenceMatcher;
import net.byteseek.matcher.multisequence.MultiSequenceMatcher;
import net.byteseek.matcher.sequence.ByteSequenceMatcher;
import net.byteseek.matcher.sequence.SequenceMatcher;
import net.byteseek.searcher.ForwardSearchIterator;
import net.byteseek.searcher.SearchResult;
import net.byteseek.searcher.Searcher;

public abstract class ByteSeekMultiBenchmark extends MultiPatternMatcherBenchmark {

	private Searcher<SequenceMatcher> searcher;

	@Override
	public void preparePatterns(Set<String> patterns) {
		this.searcher = preparePattern(patterns);
	}

	private Searcher<SequenceMatcher> preparePattern(Set<String> patterns) {
		List<SequenceMatcher> matchers = patterns.stream()
			.map(pattern -> new ByteSequenceMatcher(pattern))
			.collect(toList());
		MultiSequenceMatcher matcher = new ListMultiSequenceMatcher(matchers);
		Searcher<SequenceMatcher> searcher = create(matcher);
		searcher.prepareForwards();
		return searcher;
	}

	public abstract Searcher<SequenceMatcher> create(MultiSequenceMatcher matcher);

	@Override
	public List<Integer> find(String text) {
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
	public List<Integer> find(File file) throws IOException {
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
