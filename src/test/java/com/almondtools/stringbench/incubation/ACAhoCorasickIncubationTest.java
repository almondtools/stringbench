package com.almondtools.stringbench.incubation;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.MultiPatternMatcherBenchmark;
import com.almondtools.stringbench.MultiPatternSample;
import com.almondtools.stringbench.MultiPatternTest;

@Ignore //No algorithm should use longer than a minute for finding patterns
public class ACAhoCorasickIncubationTest extends MultiPatternTest {

	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();
	
	public MultiPatternMatcherBenchmark benchmark;
	
	@Before
	public void before() throws Exception {
		benchmark = new ACAhoCorasickBenchmark();
	}
	
	@Test (timeout=60_000)
	public void test228() throws Exception {
		MultiPatternSample sample = createSample(2, 2, 8);
		benchmark.setup(sample);
		benchmark.benchmarkFind();
		benchmark.validate();
		benchmark.tearDown();
	}

}
