package com.almondtools.stringbench.incubation;

import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.MultiPatternSample;
import com.almondtools.stringbench.MultiPatternTest;
import com.almondtools.stringbench.StringBenchIncubation;

public class ACAhoCorasickIncubationTest extends MultiPatternTest {

	@Rule
	public StringBenchIncubation incubation = new StringBenchIncubation(new ACAhoCorasickBenchmark());
	
	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();
	
	@Test
	public void test228() throws Exception {
		MultiPatternSample sample = createSample(2, 2, 8);
		incubation.benchmarkFindInString(sample);
	}

}
