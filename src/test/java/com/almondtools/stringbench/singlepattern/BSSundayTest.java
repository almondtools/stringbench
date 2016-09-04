package com.almondtools.stringbench.singlepattern;

import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.StringBenchIncubation;
import com.almondtools.stringbench.singlepattern.BSSundayBenchmark;
import com.almondtools.stringbench.singlepattern.SinglePatternSample;


public class BSSundayTest extends SinglePatternTest {

	@Rule
	public StringBenchIncubation incubation = new StringBenchIncubation(new BSSundayBenchmark());
	
	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();
	
	@Test
	public void test_2_2() throws Exception {
		SinglePatternSample sample = createSample(2, 2);
		incubation.benchmarkFindInString(sample);
	}
	
	@Test
	public void test_1024_16() throws Exception {
		SinglePatternSample sample = createSample(1024, 16);
		incubation.benchmarkFindInString(sample);
	}
	

}
