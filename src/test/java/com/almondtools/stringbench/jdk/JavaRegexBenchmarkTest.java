package com.almondtools.stringbench.jdk;

import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmark;
import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmarkTest;
import com.almondtools.stringbench.singlepattern.jdk.JavaRegexBenchmark;

public class JavaRegexBenchmarkTest extends SinglePatternMatcherBenchmarkTest {

	@Override
	protected SinglePatternMatcherBenchmark getBenchmark() {
		return new JavaRegexBenchmark();
	}
}
