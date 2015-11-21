package com.almondtools.stringbench;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MatcherBenchmarkTest {

	private static final int[] numbers = new int[] { 2, 8, 128 };
	
	@DataPoints
	public static MatcherBenchmark[] benchmark = {
		new JavaRegexBenchmark(),
		new SCHorspoolBenchmark(),
		new SCKnuthMorrisPrattBenchmark(),
		new SSBNDMBenchmark(),
		new SSBoyerMooreHorspoolBenchmark(),
		new SSBoyerMooreHorspoolRaitaBenchmark()
	};

	@DataPoints
	public static Sample[] sample = createSamples();

	@Theory
	public void testBenchmarkFind(MatcherBenchmark benchmark, Sample sample) throws Exception {
		benchmark.setup(sample);
		benchmark.benchmarkFind();
	}

	private static Sample[] createSamples() {
		List<Sample> samples = new ArrayList<>();
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				samples.add(createSample(numbers[i], numbers[j]));
			}
		}
		return samples.toArray(new Sample[0]);
	}

	private static Sample createSample(int alphabet, int pattern) {
		try {
			Sample sample = new Sample();
			sample.setAlphabetSize(alphabet);
			sample.setPatternSize(pattern);
			sample.setup();
			return sample;
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

}
