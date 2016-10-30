package com.almondtools.stringbench.multipattern.incubation.roklenarcic;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;
import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmarkTest;
import com.almondtools.stringbench.multipattern.incubation.roklenarcic.RAAhoCorasickBenchmark;

public class RAAhoCorasickBenchmarkTest extends MultiPatternMatcherBenchmarkTest {

	protected MultiPatternMatcherBenchmark getBenchmark() {
		return new RAAhoCorasickBenchmark();
	}
}
