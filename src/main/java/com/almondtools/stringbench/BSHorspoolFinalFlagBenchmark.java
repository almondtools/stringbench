package com.almondtools.stringbench;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;
import net.byteseek.matcher.sequence.ByteSequenceMatcher;
import net.byteseek.matcher.sequence.SequenceMatcher;
import net.byteseek.searcher.Searcher;
import net.byteseek.searcher.sequence.horspool.HorspoolFinalFlagSearcher;

import com.almondtools.stringbenchanalyzer.Family;

public class BSHorspoolFinalFlagBenchmark extends ByteSeekBenchmark {

	private static final String ID = "ByteSeek Horspool Final Flag";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return SUFFIX;
	}


	@Override
	public Searcher<SequenceMatcher> create(String pattern) {
		SequenceMatcher matcher = new ByteSequenceMatcher(pattern);
		return new HorspoolFinalFlagSearcher(matcher);
	}

}
