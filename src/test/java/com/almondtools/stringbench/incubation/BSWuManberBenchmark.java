package com.almondtools.stringbench.incubation;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import com.almondtools.stringbenchanalyzer.Family;

import net.byteseek.matcher.multisequence.MultiSequenceMatcher;
import net.byteseek.matcher.sequence.SequenceMatcher;
import net.byteseek.searcher.Searcher;
import net.byteseek.searcher.multisequence.set_horspool.SetHorspoolSearcher;

public class BSWuManberBenchmark extends ByteSeekMultiBenchmark {

	private static final String ID = "ByteSeek Wu Manber";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return SUFFIX;
	}


	@Override
	public Searcher<SequenceMatcher> create(MultiSequenceMatcher matcher) {
		return new SetHorspoolSearcher(matcher);
	}

}
