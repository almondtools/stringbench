package com.almondtools.stringbench.singlepattern.incubation.esmaj;

import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmark;
import com.almondtools.stringbench.singlepattern.SinglePatternMatcherBenchmarkTest;
import com.almondtools.stringbench.singlepattern.incubation.esmaj.EJSMNBenchmark;

public class EJSMNBenchmarkTest extends SinglePatternMatcherBenchmarkTest {

	protected SinglePatternMatcherBenchmark getBenchmark() {
		return new EJSMNBenchmark();
	}
}
