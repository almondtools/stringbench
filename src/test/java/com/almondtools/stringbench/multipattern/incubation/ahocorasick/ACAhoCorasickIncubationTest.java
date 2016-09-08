package com.almondtools.stringbench.multipattern.incubation.ahocorasick;

import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.StringBenchIncubation;
import com.almondtools.stringbench.multipattern.MultiPatternSample;
import com.almondtools.stringbench.multipattern.MultiPatternTest;

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
