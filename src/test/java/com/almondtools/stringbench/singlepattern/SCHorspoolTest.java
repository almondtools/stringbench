package com.almondtools.stringbench.singlepattern;

import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.StringBenchIncubation;
import com.almondtools.stringbench.singlepattern.SCHorspoolBenchmark;
import com.almondtools.stringbench.singlepattern.SinglePatternSample;

public class SCHorspoolTest extends SinglePatternTest {

	@Rule
	public StringBenchIncubation incubation = new StringBenchIncubation(new SCHorspoolBenchmark());

	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();

	@Test
	public void test22() throws Exception {
		SinglePatternSample sample = createSample(2, 2);
		incubation.benchmarkFindInString(sample);
	}

	@Test
	public void testFile22() throws Exception {
		SinglePatternSample sample = createSample(2, 2);
		incubation.benchmarkFindInFile(sample);
	}

	@Test
	public void test2256() throws Exception {
		SinglePatternSample sample = createSample(2, 256);
		incubation.benchmarkFindInString(sample);
	}

	@Test
	public void testFile2256() throws Exception {
		SinglePatternSample sample = createSample(2, 256);
		incubation.benchmarkFindInFile(sample);
	}

}
