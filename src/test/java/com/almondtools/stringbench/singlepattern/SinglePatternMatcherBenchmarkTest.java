package com.almondtools.stringbench.singlepattern;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import com.almondtools.stringbench.CompareResultNotAccepted;

@RunWith(Theories.class)
public class SinglePatternMatcherBenchmarkTest extends SinglePatternTest {

	@Rule
	public Stopwatch watch = new Stopwatch() {
		protected void succeeded(long nanos, Description description) {
			System.out.println("time: " + (nanos / 1_000_000) + " milliseconds.");
		};
	};
	
	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();
	
	private static final int[] ALPHABET = new int[] { 2, 4, 16, 128 };
	private static final int[] SIZE = new int[] { 2, 8, 64 };

	@DataPoints
	public static SinglePatternMatcherBenchmark[] benchmark = {
		new JavaIndexOfBenchmark(),
		new JavaRegexBenchmark(),
		
		new SCHorspoolBenchmark(),
		new SCKnuthMorrisPrattBenchmark(),
		new SCSundayBenchmark(),
		new SCShiftAndBenchmark(),
		new SCBNDMBenchmark(),
		
		new BSBoyerMooreHorspoolBenchmark(),
		new BSHorspoolFinalFlagBenchmark(),
		new BSSundayBenchmark()
		
	};

	@DataPoints
	public static SinglePatternSample[] sample = createSamples();

	@Before
	public void before() throws Exception {
		System.gc();
	}
	
	@Theory
	public void testBenchmarkFindInString(SinglePatternMatcherBenchmark benchmark, SinglePatternSample sample) throws Exception {
		System.out.println("[Search in String] " + benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		benchmark.benchmarkFindInString();
		benchmark.tearDown();
	}

	@Theory
	public void testBenchmarkFindInFile(SinglePatternMatcherBenchmark benchmark, SinglePatternSample sample) throws Exception {
		System.out.println("[Search in File] " + benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		benchmark.benchmarkFindInFile();
		benchmark.tearDown();
	}

	private static SinglePatternSample[] createSamples() {
		List<SinglePatternSample> samples = new ArrayList<>();
		for (int i = 0; i < ALPHABET.length; i++) {
			for (int j = 0; j < SIZE.length; j++) {
				SinglePatternSample sample = createSample(ALPHABET[i], SIZE[j]);
				if (sample.isValid()) {
					samples.add(sample);
				}
			}
		}
		return samples.toArray(new SinglePatternSample[0]);
	}

}