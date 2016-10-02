package com.almondtools.stringbench.singlepattern.incubation.esmaj;

import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmark;
import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmarkTest;
import com.almondtools.stringbench.singlepattern.incubation.esmaj.EJSOBenchmark;

public class EJSOBenchmarkTest extends SinglePatternMatcherBenchmarkTest {

	protected SinglePatternMatcherBenchmark getBenchmark() {
		return new EJSOBenchmark();
	}
}
