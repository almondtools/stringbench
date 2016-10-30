package com.almondtools.stringbench.multipattern.incubation.roklenarcic;

import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.StringBenchIncubation;
import com.almondtools.stringbench.multipattern.MultiPatternSample;
import com.almondtools.stringbench.multipattern.MultiPatternTest;

public class RAAhoCorasickIncubationTest extends MultiPatternTest {

	@Rule
	public StringBenchIncubation incubation = new StringBenchIncubation(new RAAhoCorasickBenchmark());
	
	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();
	
	@Test
	public void test512_2_32() throws Exception {
		MultiPatternSample sample = createSample(512, 2, 32);
		incubation.benchmarkFindInString(sample);
	}

	@Test
	public void test512_4_32() throws Exception {
		MultiPatternSample sample = createSample(512, 4, 32);
		incubation.benchmarkFindInString(sample);
	}

}
