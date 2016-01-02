package com.almondtools.stringbench.incubation;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.SinglePatternMatcherBenchmark;
import com.almondtools.stringbench.SinglePatternSample;
import com.almondtools.stringbench.SinglePatternTest;

@Ignore //No algorithm should use longer than a minute for finding patterns
public class SSBoyerMooreHorspoolRaitaIncubationTest extends SinglePatternTest {

	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();
	
	public SinglePatternMatcherBenchmark benchmark;
	
	@Before
	public void before() throws Exception {
		benchmark = new SSBoyerMooreHorspoolRaitaBenchmark();
	}
	
	@Test (timeout=60_000)
	public void test22() throws Exception {
		SinglePatternSample sample = createSample(2, 2);
		benchmark.setup(sample);
		benchmark.benchmarkFind();
		benchmark.tearDown();
	}

}
