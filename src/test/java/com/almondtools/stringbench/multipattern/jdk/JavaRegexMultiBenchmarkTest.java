package com.almondtools.stringbench.multipattern.jdk;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;
import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmarkTest;

public class JavaRegexMultiBenchmarkTest extends MultiPatternMatcherBenchmarkTest {

	protected MultiPatternMatcherBenchmark getBenchmark() {
		return new JavaRegexMultiBenchmark();
	}
}
