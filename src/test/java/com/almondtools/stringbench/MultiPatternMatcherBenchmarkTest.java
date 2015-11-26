package com.almondtools.stringbench;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MultiPatternMatcherBenchmarkTest {

	private static final int[] numbers = new int[] { 2, 64 };
	
	@DataPoints
	public static MultiPatternMatcherBenchmark[] benchmark = {
		new JavaIndexOfMultiBenchmark(),
		new JavaRegexMultiBenchmark(),
		new SCSetHorspoolBenchmark(),
		new SCAhoCorasickBenchmark(),
		new SCSetBackwardOracleMatchingBenchmark(),
		new SCWuManberBenchmark(),
		new ACAhoCorasickBenchmark()
	};

	@DataPoints
	public static MultiPatternSample[] sample = createSamples();

	@Theory
	public void testBenchmarkFind(MultiPatternMatcherBenchmark benchmark, MultiPatternSample sample) throws Exception {
		System.out.println(benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		benchmark.benchmarkFind();
	}

	private static MultiPatternSample[] createSamples() {
		List<MultiPatternSample> samples = new ArrayList<>();
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				samples.add(createSample(numbers[i], numbers[j]));
			}
		}
		return samples.toArray(new MultiPatternSample[0]);
	}

	private static MultiPatternSample createSample(int alphabet, int pattern) {
		try {
			System.out.println("creating sample with alphabet size " + alphabet + " and pattern size " + pattern);
			MultiPatternSample sample = new MultiPatternSample();
			sample.setAlphabetSize(alphabet);
			sample.setPatternSize(pattern);
			sample.setup();
			return sample;
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

}
