package com.almondtools.stringbench.singlepattern.stringsearchalgorithms;

import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmark;
import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmarkTest;

public class SCKnuthMorrisPrattBenchmarkTest extends SinglePatternMatcherBenchmarkTest {

	@Override
	protected SinglePatternMatcherBenchmark getBenchmark() {
		return new SCKnuthMorrisPrattBenchmark();
	}
}
