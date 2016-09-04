package com.almondtools.stringbench.singlepattern;

import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.StringBenchIncubation;
import com.almondtools.stringbench.singlepattern.BSBoyerMooreHorspoolBenchmark;
import com.almondtools.stringbench.singlepattern.SinglePatternSample;


public class BSBoyerMooreHorspoolTest extends SinglePatternTest {

	@Rule
	public StringBenchIncubation incubation = new StringBenchIncubation(new BSBoyerMooreHorspoolBenchmark());
	
	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();
	
	@Test
	public void test_2_2() throws Exception {
		SinglePatternSample sample = createSample(2, 2);
		incubation.benchmarkFindInString(sample);
	}

	@Test
	public void testFile_2_2() throws Exception {
		SinglePatternSample sample = createSample(2, 2);
		incubation.benchmarkFindInFile(sample);
	}

	@Test
	public void testFile_256_2() throws Exception {
		SinglePatternSample sample = createSample(256, 2);
		incubation.benchmarkFindInFile(sample);
	}

}
