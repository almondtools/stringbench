package com.almondtools.stringbench.incubation;

import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.BSWuManberBenchmark;
import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.MultiPatternSample;
import com.almondtools.stringbench.MultiPatternTest;
import com.almondtools.stringbench.StringBenchIncubation;

public class BSWuManberIncubationTest extends MultiPatternTest {

	@Rule
	public StringBenchIncubation incubation = new StringBenchIncubation(new BSWuManberBenchmark());

	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();

	@Test
	public void test_2_32_2() throws Exception {
		MultiPatternSample sample = createSample(2, 8, 2);
		incubation.benchmarkFindInFile(sample);
	}

}
