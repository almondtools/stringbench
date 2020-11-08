package com.almondtools.stringbench.multipattern.incubation.roklenarcic;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;
import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmarkTest;

public class RAAhoCorasickBenchmarkTest extends MultiPatternMatcherBenchmarkTest {

	protected MultiPatternMatcherBenchmark getBenchmark() {
		return new RAAhoCorasickBenchmark();
	}
}
