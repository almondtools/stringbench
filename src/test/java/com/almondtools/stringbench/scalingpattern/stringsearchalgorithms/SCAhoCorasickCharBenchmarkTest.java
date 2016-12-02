package com.almondtools.stringbench.scalingpattern.stringsearchalgorithms;

import org.junit.Test;

import com.almondtools.stringbench.scalingpattern.ScalingPatternMatcherBenchmark;
import com.almondtools.stringbench.scalingpattern.ScalingPatternMatcherBenchmarkTest;

public class SCAhoCorasickCharBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new SCAhoCorasickCharBenchmark();
	}

	@Test
	public void test() throws Exception {
		findInFileSample("human-protein", 459);
	}

}
