package com.almondtools.stringbench.singlepattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import com.almondtools.stringbench.CompareResultNotAccepted;

public abstract class SinglePatternMatcherBenchmarkTest extends SinglePatternTest {

	@Rule
	public Stopwatch watch = new Stopwatch() {
		protected void succeeded(long nanos, Description description) {
			System.out.println("time: " + (nanos / 1_000_000) + " milliseconds.");
		};
	};

	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();

	private SinglePatternMatcherBenchmark benchmark;

	protected abstract SinglePatternMatcherBenchmark getBenchmark();

	@Before
	public void before() throws Exception {
		benchmark = getBenchmark();
	}

	@After
	public void after() throws Exception {
		benchmark.tearDown();
		benchmark = null;
	}

	public void findInStringSample(int alphabet, int pattern) {
		SinglePatternSample sample = createSample(alphabet, pattern);
		System.out.println("[Search in String] " + benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		benchmark.benchmarkFindInString();
		benchmark.validate();
	}

	public void findInFileSample(int alphabet, int pattern) throws Exception {
		SinglePatternSample sample = createSample(alphabet, pattern);
		System.out.println("[Search in String] " + benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		benchmark.benchmarkFindInFile();
		benchmark.validate();
	}

	@Test
	public void testBenchmarkFindInString_2_2() throws Exception {
		findInStringSample(2, 2);
	}

	@Test
	public void testBenchmarkFindInFile_2_2() throws Exception {
		findInFileSample(2, 2);
	}

	@Test
	public void testBenchmarkFindInString_4_2() throws Exception {
		findInStringSample(4, 2);
	}

	@Test
	public void testBenchmarkFindInFile_4_2() throws Exception {
		findInFileSample(4, 2);
	}

	@Test
	public void testBenchmarkFindInString_16_2() throws Exception {
		findInStringSample(16, 2);
	}

	@Test
	public void testBenchmarkFindInFile_16_2() throws Exception {
		findInFileSample(16, 2);
	}

	@Test
	public void testBenchmarkFindInString_128_2() throws Exception {
		findInStringSample(128, 2);
	}

	@Test
	public void testBenchmarkFindInFile_128_2() throws Exception {
		findInFileSample(128, 2);
	}

	@Test
	public void testBenchmarkFindInString_2_8() throws Exception {
		findInStringSample(2, 8);
	}

	@Test
	public void testBenchmarkFindInFile_2_8() throws Exception {
		findInFileSample(2, 8);
	}

	@Test
	public void testBenchmarkFindInString_4_8() throws Exception {
		findInStringSample(4, 8);
	}

	@Test
	public void testBenchmarkFindInFile_4_8() throws Exception {
		findInFileSample(4, 8);
	}

	@Test
	public void testBenchmarkFindInString_16_8() throws Exception {
		findInStringSample(16, 8);
	}

	@Test
	public void testBenchmarkFindInFile_16_8() throws Exception {
		findInFileSample(16, 8);
	}

	@Test
	public void testBenchmarkFindInString_128_8() throws Exception {
		findInStringSample(128, 8);
	}

	@Test
	public void testBenchmarkFindInFile_128_8() throws Exception {
		findInFileSample(128, 8);
	}

	@Test
	public void testBenchmarkFindInString_2_64() throws Exception {
		findInStringSample(2, 64);
	}

	@Test
	public void testBenchmarkFindInFile_2_64() throws Exception {
		findInFileSample(2, 64);
	}

	@Test
	public void testBenchmarkFindInString_4_64() throws Exception {
		findInStringSample(4, 64);
	}

	@Test
	public void testBenchmarkFindInFile_4_64() throws Exception {
		findInFileSample(4, 64);
	}

	@Test
	public void testBenchmarkFindInString_16_64() throws Exception {
		findInStringSample(16, 64);
	}

	@Test
	public void testBenchmarkFindInFile_16_64() throws Exception {
		findInFileSample(16, 64);
	}

	@Test
	public void testBenchmarkFindInString_128_64() throws Exception {
		findInStringSample(128, 64);
	}

	@Test
	public void testBenchmarkFindInFile_128_64() throws Exception {
		findInFileSample(128, 64);
	}

	@Test
	public void testBenchmarkFindInString_1024_1024() throws Exception {
		findInStringSample(1024, 1024);
	}

	@Test
	public void testBenchmarkFindInFile_1024_1024() throws Exception {
		findInFileSample(1024, 1024);
	}

}
