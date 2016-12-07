package com.almondtools.stringbench.scalingpattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import com.almondtools.stringbench.CompareResultNotAccepted;

public abstract class ScalingPatternMatcherBenchmarkTest extends ScalingPatternTest {

	@Rule
	public Stopwatch watch = new Stopwatch() {
		protected void succeeded(long nanos, Description description) {
			System.out.println("time: " + (nanos / 1_000_000) + " milliseconds.");
		};
	};
	
	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();

	private ScalingPatternMatcherBenchmark benchmark;

	protected abstract ScalingPatternMatcherBenchmark getBenchmark();

	@Before
	public void before() throws Exception {
		benchmark = getBenchmark();
	}

	@After
	public void after() throws Exception {
		benchmark.tearDown();
		benchmark = null;
	}

	public void findInStringSample(String corpus, int number) {
		ScalingPatternSample sample = createSample(corpus, number);
		System.out.println("[Search in String] " + benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		benchmark.benchmarkFindInString();
		benchmark.validate();
	}

	public void findInFileSample(String corpus, int number) throws Exception {
		ScalingPatternSample sample = createSample(corpus, number);
		System.out.println("[Search in String] " + benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		benchmark.benchmarkFindInFile();
		benchmark.validate();
	}

	@Test
	public void testBenchmarkFindInString_ecoli_1250() {
		findInStringSample("ecoli", 1250);
	}

	@Test
	public void testBenchmarkFindInString_ecoli_2500() {
		findInStringSample("ecoli", 2500);
	}

	@Test
	public void testBenchmarkFindInString_ecoli_5000() {
		findInStringSample("ecoli", 5000);
	}

	@Test
	public void testBenchmarkFindInString_ecoli_10000() {
		findInStringSample("ecoli", 10000);
	}

	@Test
	public void testBenchmarkFindInString_ecoli_20000() {
		findInStringSample("ecoli", 20000);
	}

	@Test
	public void testBenchmarkFindInString_humanprotein_1250() {
		findInStringSample("human-protein", 1250);
	}

	@Test
	public void testBenchmarkFindInString_humanprotein_2500() {
		findInStringSample("human-protein", 2500);
	}

	@Test
	public void testBenchmarkFindInString_humanprotein_5000() {
		findInStringSample("human-protein", 5000);
	}

	@Test
	public void testBenchmarkFindInString_humanprotein_10000() {
		findInStringSample("human-protein", 10000);
	}

	@Test
	public void testBenchmarkFindInString_humanprotein_20000() {
		findInStringSample("human-protein", 20000);
	}
	
	@Test
	public void testBenchmarkFindInString_kjb_1250() {
		findInStringSample("kjb", 1250);
	}

	@Test
	public void testBenchmarkFindInString_kjb_2500() {
		findInStringSample("kjb", 2500);
	}

	@Test
	public void testBenchmarkFindInString_kjb_5000() {
		findInStringSample("kjb", 5000);
	}

	@Test
	public void testBenchmarkFindInString_kjb_10000() {
		findInStringSample("kjb", 10000);
	}

	@Test
	public void testBenchmarkFindInString_kjb_20000() {
		findInStringSample("kjb", 20000);
	}

	@Test
	public void testBenchmarkFindInFile_ecoli_1250() throws Exception {
		findInFileSample("ecoli", 1250);
	}

	@Test
	public void testBenchmarkFindInFile_ecoli_2500() throws Exception {
		findInFileSample("ecoli", 2500);
	}

	@Test
	public void testBenchmarkFindInFile_ecoli_5000() throws Exception {
		findInFileSample("ecoli", 5000);
	}

	@Test
	public void testBenchmarkFindInFile_ecoli_10000() throws Exception {
		findInFileSample("ecoli", 10000);
	}

	@Test
	public void testBenchmarkFindInFile_ecoli_20000() throws Exception {
		findInFileSample("ecoli", 20000);
	}

	@Test
	public void testBenchmarkFindInFile_humanprotein_1250() throws Exception {
		findInFileSample("human-protein", 1250);
	}

	@Test
	public void testBenchmarkFindInFile_humanprotein_2500() throws Exception {
		findInFileSample("human-protein", 2500);
	}

	@Test
	public void testBenchmarkFindInFile_humanprotein_5000() throws Exception {
		findInFileSample("human-protein", 5000);
	}

	@Test
	public void testBenchmarkFindInFile_humanprotein_10000() throws Exception {
		findInFileSample("human-protein", 10000);
	}

	@Test
	public void testBenchmarkFindInFile_humanprotein_20000() throws Exception {
		findInFileSample("human-protein", 20000);
	}
	@Test
	public void testBenchmarkFindInFile_kjb_1250() throws Exception {
		findInFileSample("kjb", 1250);
	}

	@Test
	public void testBenchmarkFindInFile_kjb_2500() throws Exception {
		findInFileSample("kjb", 2500);
	}

	@Test
	public void testBenchmarkFindInFile_kjb_5000() throws Exception {
		findInFileSample("kjb", 5000);
	}

	@Test
	public void testBenchmarkFindInFile_kjb_10000() throws Exception {
		findInFileSample("kjb", 10000);
	}

	@Test
	public void testBenchmarkFindInFile_kjb_20000() throws Exception {
		findInFileSample("kjb", 20000);
	}
}
