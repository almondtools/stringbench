package com.almondtools.stringbench.incubation;

import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.BSSundayBenchmark;
import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.SinglePatternSample;
import com.almondtools.stringbench.SinglePatternTest;
import com.almondtools.stringbench.StringBenchIncubation;


public class BSSundayIncubationTest extends SinglePatternTest {

	@Rule
	public StringBenchIncubation incubation = new StringBenchIncubation(new BSSundayBenchmark());
	
	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();
	
	@Test
	public void test22() throws Exception {
		SinglePatternSample sample = createSample(2, 2);
		incubation.benchmarkFindInString(sample);
	}

}
