package com.almondtools.stringbench.jdk;

import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmark;
import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmarkTest;
import com.almondtools.stringbench.singlepattern.jdk.JavaIndexOfBenchmark;

public class JavaIndexOfBenchmarkTest extends SinglePatternMatcherBenchmarkTest {

	@Override
	protected SinglePatternMatcherBenchmark getBenchmark() {
		return new JavaIndexOfBenchmark();
	}

}
