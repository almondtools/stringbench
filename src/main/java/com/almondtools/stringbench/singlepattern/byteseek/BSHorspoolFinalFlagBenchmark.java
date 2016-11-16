package com.almondtools.stringbench.singlepattern.byteseek;

import net.byteseek.matcher.sequence.SequenceMatcher;
import net.byteseek.searcher.Searcher;
import net.byteseek.searcher.sequence.horspool.HorspoolFinalFlagSearcher;

public class BSHorspoolFinalFlagBenchmark extends ByteSeekBenchmark {

	private static final String ID = "ByteSeek Boyer-Moore-Horspool final flag";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Searcher<SequenceMatcher> create(SequenceMatcher matcher) {
		return new HorspoolFinalFlagSearcher(matcher);
	}

}
