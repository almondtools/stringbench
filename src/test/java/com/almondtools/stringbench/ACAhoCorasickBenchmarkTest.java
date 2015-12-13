package com.almondtools.stringbench;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

@Ignore
public class ACAhoCorasickBenchmarkTest {

	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();

	@Test
	public void testACAhoCorasick2_2_8() {
		MultiPatternMatcherBenchmark benchmark = new ACAhoCorasickBenchmark();
		benchmark.setup(createMultiPatternSample(2, 2, 8));
		benchmark.benchmarkFind();
	}

	@Test
	public void testACAhoCorasick4_128_8() {
		MultiPatternMatcherBenchmark benchmark = new ACAhoCorasickBenchmark();
		benchmark.setup(createMultiPatternSample(4, 128, 8));
		benchmark.benchmarkFind();
	}

	@Test
	public void testACAhoCorasick8_128_256() {
		MultiPatternMatcherBenchmark benchmark = new ACAhoCorasickBenchmark();
		benchmark.setup(createMultiPatternSample(8, 256, 8));
		benchmark.benchmarkFind();
	}

	private static MultiPatternSample createMultiPatternSample(int alphabet, int patternNumber, int patternSize) {
		try {
			MultiPatternSample sample = new MultiPatternSample();
			sample.setPatternNumber(patternNumber);
			sample.setAlphabetSize(alphabet);
			sample.setPatternSize(patternSize);
			sample.setup();
			return sample;
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

}
