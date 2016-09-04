package com.almondtools.stringbench.multipattern.incubation;

import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.StringBenchIncubation;
import com.almondtools.stringbench.multipattern.MultiPatternSample;
import com.almondtools.stringbench.multipattern.MultiPatternTest;

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
