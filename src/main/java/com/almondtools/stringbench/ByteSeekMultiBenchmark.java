package com.almondtools.stringbench;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.byteseek.matcher.sequence.SequenceMatcher;
import net.byteseek.searcher.ForwardSearchIterator;
import net.byteseek.searcher.SearchResult;
import net.byteseek.searcher.Searcher;

public abstract class ByteSeekMultiBenchmark extends MultiPatternMatcherBenchmark {

	private Searcher<SequenceMatcher> searcher;

	@Override
	public void prepare(Set<String> patterns) {
		this.searcher = create(patterns);
	}

	public abstract Searcher<SequenceMatcher> create(Set<String> patterns);

	@Override
	public List<Integer> find(String text) {
		byte[] stringBytes = text.getBytes();
		ForwardSearchIterator<SequenceMatcher> searchIterator = new
			ForwardSearchIterator<SequenceMatcher>(searcher, stringBytes, 0);

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
