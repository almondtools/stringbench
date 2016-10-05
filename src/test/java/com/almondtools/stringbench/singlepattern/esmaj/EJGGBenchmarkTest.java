package com.almondtools.stringbench.singlepattern.esmaj;

import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmark;
import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmarkTest;
import com.almondtools.stringbench.singlepattern.esmaj.EJGGBenchmark;

public class EJGGBenchmarkTest extends SinglePatternMatcherBenchmarkTest {

	protected SinglePatternMatcherBenchmark getBenchmark() {
		return new EJGGBenchmark();
	}
}
