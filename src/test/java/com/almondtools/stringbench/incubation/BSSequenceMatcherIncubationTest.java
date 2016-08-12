package com.almondtools.stringbench.incubation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.BSSequenceMatcherBenchmark;
import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.SinglePatternMatcherBenchmark;
import com.almondtools.stringbench.SinglePatternSample;
import com.almondtools.stringbench.SinglePatternTest;

public class BSSequenceMatcherIncubationTest extends SinglePatternTest {

	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();
	
	public SinglePatternMatcherBenchmark benchmark;
	
	@Before
	public void before() throws Exception {
		benchmark = new BSSequenceMatcherBenchmark();
	}
	
	@Test (timeout=60_000)
	public void test22() throws Exception {
		SinglePatternSample sample = createSample(2, 2);
		benchmark.setup(sample);
		benchmark.benchmarkFind();
		benchmark.validate();
		benchmark.tearDown();
	}

}
