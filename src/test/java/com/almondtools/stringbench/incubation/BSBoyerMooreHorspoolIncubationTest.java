package com.almondtools.stringbench.incubation;

import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.BSBoyerMooreHorspoolBenchmark;
import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.SinglePatternSample;
import com.almondtools.stringbench.SinglePatternTest;
import com.almondtools.stringbench.StringBenchIncubation;


public class BSBoyerMooreHorspoolIncubationTest extends SinglePatternTest {

	@Rule
	public StringBenchIncubation incubation = new StringBenchIncubation(new BSBoyerMooreHorspoolBenchmark());
	
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
	public void testFile2256() throws Exception {
		SinglePatternSample sample = createSample(2, 256);
		incubation.benchmarkFindInFile(sample);
	}

}
