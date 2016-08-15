package com.almondtools.stringbench;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MultiPatternMatcherBenchmarkTest extends MultiPatternTest {

	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();

	private static final int[] ALPHABET = new int[] { 2, 4, 16, 128 };
	private static final int[] SIZE = new int[] { 2, 8, 64 };
	private static final int[] NUMBER = new int[] { 2, 32 };

	@DataPoints
	public static MultiPatternMatcherBenchmark[] benchmark = {
		new JavaIndexOfMultiBenchmark(),
		new JavaRegexMultiBenchmark(),
		
		new SCSetHorspoolBenchmark(),
		new SCAhoCorasickBenchmark(),
		new SCWuManberBenchmark(),
		new SCSetBackwardOracleMatchingBenchmark(), 
		
		new BSSetHorspoolBenchmark(),
		new BSSetHorspoolFinalFlagBenchmark(),
		new BSWuManberBenchmark()
	};

	@DataPoints
	public static MultiPatternSample[] sample = createSamples();

	@Theory
	public void testBenchmarkFind(MultiPatternMatcherBenchmark benchmark, MultiPatternSample sample) throws Exception {
		System.out.println(benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		benchmark.benchmarkFind();
		benchmark.tearDown();
	}

	private static MultiPatternSample[] createSamples() {
		List<MultiPatternSample> samples = new ArrayList<>();
		for (int i = 0; i < ALPHABET.length; i++) {
			for (int j = 0; j < SIZE.length; j++) {
				for (int k = 0; k < NUMBER.length; k++) {
					MultiPatternSample sample = createSample(ALPHABET[i], SIZE[j], NUMBER[k]);
					if (sample.isValid()) {
						samples.add(sample);
					}
				}
			}
		}
		return samples.toArray(new MultiPatternSample[0]);
	}

}
