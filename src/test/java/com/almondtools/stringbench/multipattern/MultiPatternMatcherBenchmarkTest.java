package com.almondtools.stringbench.multipattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import com.almondtools.stringbench.CompareResultNotAccepted;

public abstract class MultiPatternMatcherBenchmarkTest extends MultiPatternTest {

	@Rule
	public Stopwatch watch = new Stopwatch() {
		protected void succeeded(long nanos, Description description) {
			System.out.println("time: " + (nanos / 1_000_000) + " milliseconds.");
		};
	};
	
	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();

	private MultiPatternMatcherBenchmark benchmark;

	protected abstract MultiPatternMatcherBenchmark getBenchmark();

	@Before
	public void before() throws Exception {
		benchmark = getBenchmark();
	}

	@After
	public void after() throws Exception {
		benchmark.tearDown();
		benchmark = null;
	}

	public void findInStringSample(int alphabet, int pattern, int number) {
		MultiPatternSample sample = createSample(alphabet, pattern, number);
		System.out.println("[Search in String] " + benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		benchmark.benchmarkFindInString();
	}

	public void findInFileSample(int alphabet, int pattern, int number) throws Exception {
		MultiPatternSample sample = createSample(alphabet, pattern, number);
		System.out.println("[Search in String] " + benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		benchmark.benchmarkFindInFile();
	}

	@Test
	public void testBenchmarkFindInString_2_2_2() {
		findInStringSample(2, 2, 2);
	}

	@Test
	public void testBenchmarkFindInFile_2_2_2() throws Exception {
		findInFileSample(2, 2, 2);
	}

	@Test
	public void testBenchmarkFindInString_4_2_2() {
		findInStringSample(4, 2, 2);
	}

	@Test
	public void testBenchmarkFindInFile_4_2_2() throws Exception {
		findInFileSample(4, 2, 2);
	}

	@Test
	public void testBenchmarkFindInString_16_2_2() {
		findInStringSample(16, 2, 2);
	}

	@Test
	public void testBenchmarkFindInFile_16_2_2() throws Exception {
		findInFileSample(16, 2, 2);
	}

	@Test
	public void testBenchmarkFindInString_128_2_2() {
		findInStringSample(128, 2, 2);
	}

	@Test
	public void testBenchmarkFindInFile_128_2_2() throws Exception {
		findInFileSample(128, 2, 2);
	}

	@Test
	public void testBenchmarkFindInString_2_8_2() {
		findInStringSample(2, 8, 2);
	}

	@Test
	public void testBenchmarkFindInFile_2_8_2() throws Exception {
		findInFileSample(2, 8, 2);
	}

	@Test
	public void testBenchmarkFindInString_4_8_2() {
		findInStringSample(4, 8, 2);
	}

	@Test
	public void testBenchmarkFindInFile_4_8_2() throws Exception {
		findInFileSample(4, 8, 2);
	}

	@Test
	public void testBenchmarkFindInString_16_8_2() {
		findInStringSample(16, 8, 2);
	}

	@Test
	public void testBenchmarkFindInFile_16_8_2() throws Exception {
		findInFileSample(16, 8, 2);
	}

	@Test
	public void testBenchmarkFindInString_128_8_2() {
		findInStringSample(128, 8, 2);
	}

	@Test
	public void testBenchmarkFindInFile_128_8_2() throws Exception {
		findInFileSample(128, 8, 2);
	}

	@Test
	public void testBenchmarkFindInString_2_64_2() {
		findInStringSample(2, 64, 2);
	}

	@Test
	public void testBenchmarkFindInFile_2_64_2() throws Exception {
		findInFileSample(2, 64, 2);
	}

	@Test
	public void testBenchmarkFindInString_4_64_2() {
		findInStringSample(4, 64, 2);
	}

	@Test
	public void testBenchmarkFindInFile_4_64_2() throws Exception {
		findInFileSample(4, 64, 2);
	}

	@Test
	public void testBenchmarkFindInString_16_64_2() {
		findInStringSample(16, 64, 2);
	}

	@Test
	public void testBenchmarkFindInFile_16_64_2() throws Exception {
		findInFileSample(16, 64, 2);
	}

	@Test
	public void testBenchmarkFindInString_128_64_2() {
		findInStringSample(128, 64, 2);
	}

	@Test
	public void testBenchmarkFindInFile_128_64_2() throws Exception {
		findInFileSample(128, 64, 2);
	}

	@Test
	public void testBenchmarkFindInString_2_2_32() {
		findInStringSample(2, 2, 32);
	}

	@Test
	public void testBenchmarkFindInFile_2_2_32() throws Exception {
		findInFileSample(2, 2, 32);
	}

	@Test
	public void testBenchmarkFindInString_4_2_32() {
		findInStringSample(4, 2, 32);
	}

	@Test
	public void testBenchmarkFindInFile_4_2_32() throws Exception {
		findInFileSample(4, 2, 32);
	}

	@Test
	public void testBenchmarkFindInString_16_2_32() {
		findInStringSample(16, 2, 32);
	}

	@Test
	public void testBenchmarkFindInFile_16_2_32() throws Exception {
		findInFileSample(16, 2, 32);
	}

	@Test
	public void testBenchmarkFindInString_128_2_32() {
		findInStringSample(128, 2, 32);
	}

	@Test
	public void testBenchmarkFindInFile_128_2_32() throws Exception {
		findInFileSample(128, 2, 32);
	}

	@Test
	public void testBenchmarkFindInString_2_8_32() {
		findInStringSample(2, 8, 32);
	}

	@Test
	public void testBenchmarkFindInFile_2_8_32() throws Exception {
		findInFileSample(2, 8, 32);
	}

	@Test
	public void testBenchmarkFindInString_4_8_32() {
		findInStringSample(4, 8, 32);
	}

	@Test
	public void testBenchmarkFindInFile_4_8_32() throws Exception {
		findInFileSample(4, 8, 32);
	}

	@Test
	public void testBenchmarkFindInString_16_8_32() {
		findInStringSample(16, 8, 32);
	}

	@Test
	public void testBenchmarkFindInFile_16_8_32() throws Exception {
		findInFileSample(16, 8, 32);
	}

	@Test
	public void testBenchmarkFindInString_128_8_32() {
		findInStringSample(128, 8, 32);
	}

	@Test
	public void testBenchmarkFindInFile_128_8_32() throws Exception {
		findInFileSample(128, 8, 32);
	}

	@Test
	public void testBenchmarkFindInString_2_64_32() {
		findInStringSample(2, 64, 32);
	}

	@Test
	public void testBenchmarkFindInFile_2_64_32() throws Exception {
		findInFileSample(2, 64, 32);
	}

	@Test
	public void testBenchmarkFindInString_4_64_32() {
		findInStringSample(4, 64, 32);
	}

	@Test
	public void testBenchmarkFindInFile_4_64_32() throws Exception {
		findInFileSample(4, 64, 32);
	}

	@Test
	public void testBenchmarkFindInString_16_64_32() {
		findInStringSample(16, 64, 32);
	}

	@Test
	public void testBenchmarkFindInFile_16_64_32() throws Exception {
		findInFileSample(16, 64, 32);
	}

	@Test
	public void testBenchmarkFindInString_128_64_32() {
		findInStringSample(128, 64, 32);
	}

	@Test
	public void testBenchmarkFindInFile_128_64_32() throws Exception {
		findInFileSample(128, 64, 32);
	}

}
