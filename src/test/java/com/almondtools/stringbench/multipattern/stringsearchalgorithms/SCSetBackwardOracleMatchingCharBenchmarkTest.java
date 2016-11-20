package com.almondtools.stringbench.multipattern.stringsearchalgorithms;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;
import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmarkTest;

public class SCSetBackwardOracleMatchingCharBenchmarkTest extends MultiPatternMatcherBenchmarkTest {

	protected MultiPatternMatcherBenchmark getBenchmark() {
		return new SCSetBackwardOracleMatchingCharBenchmark();
	}
}
