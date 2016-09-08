package com.almondtools.stringbench.singlepattern.byteseek;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import com.almondtools.stringbenchanalyzer.Family;

import net.byteseek.matcher.sequence.SequenceMatcher;
import net.byteseek.searcher.Searcher;
import net.byteseek.searcher.sequence.sunday.SundayQuickSearcher;

public class BSSundayBenchmark extends ByteSeekBenchmark {

	private static final String ID = "ByteSeek Sunday";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return SUFFIX;
	}


	@Override
	public Searcher<SequenceMatcher> create(SequenceMatcher matcher) {
		return new SundayQuickSearcher(matcher);
	}

}
