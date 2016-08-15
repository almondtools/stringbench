package com.almondtools.stringbench;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Set;

import net.byteseek.matcher.multisequence.ListMultiSequenceMatcher;
import net.byteseek.matcher.multisequence.MultiSequenceMatcher;
import net.byteseek.matcher.sequence.ByteSequenceMatcher;
import net.byteseek.matcher.sequence.SequenceMatcher;
import net.byteseek.searcher.Searcher;
import net.byteseek.searcher.multisequence.set_horspool.SetHorspoolFinalFlagSearcher;

import com.almondtools.stringbenchanalyzer.Family;

public class BSSetHorspoolFinalFlagBenchmark extends ByteSeekMultiBenchmark {

	private static final String ID = "ByteSeek Set Horspool final flag";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return SUFFIX;
	}


	@Override
	public Searcher<SequenceMatcher> create(Set<String> patterns) {
		List<SequenceMatcher> matchers = patterns.stream()
			.map(pattern -> new ByteSequenceMatcher(pattern))
			.collect(toList());
		MultiSequenceMatcher matcher = new ListMultiSequenceMatcher(matchers);
		return new SetHorspoolFinalFlagSearcher(matcher);
	}

}
