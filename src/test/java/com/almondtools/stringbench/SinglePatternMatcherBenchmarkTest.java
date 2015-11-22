package com.almondtools.stringbench;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class SinglePatternMatcherBenchmarkTest {

	private static final int[] numbers = new int[] { 2, 8, 128 };
	
	@DataPoints
	public static SinglePatternMatcherBenchmark[] benchmark = {
		new JavaRegexBenchmark(),
		new SCHorspoolBenchmark(),
		new SCKnuthMorrisPrattBenchmark(),
		new SCSundayBenchmark(),
		new SSBNDMBenchmark(),
		new SSBoyerMooreHorspoolBenchmark(),
		new SSBoyerMooreHorspoolRaitaBenchmark()
	};

	@DataPoints
	public static SinglePatternSample[] sample = createSamples();

	@Theory
	public void testBenchmarkFind(SinglePatternMatcherBenchmark benchmark, SinglePatternSample sample) throws Exception {
		System.out.println(benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		benchmark.benchmarkFind();
	}

	private static SinglePatternSample[] createSamples() {
		List<SinglePatternSample> samples = new ArrayList<>();
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				samples.add(createSample(numbers[i], numbers[j]));
			}
		}
		return samples.toArray(new SinglePatternSample[0]);
	}

	private static SinglePatternSample createSample(int alphabet, int pattern) {
		try {
			System.out.println("creating sample with alphabet size " + alphabet + " and pattern size " + pattern);
			SinglePatternSample sample = new SinglePatternSample();
			sample.setAlphabetSize(alphabet);
			sample.setPatternSize(pattern);
			sample.setup();
			return sample;
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

}
