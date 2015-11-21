package com.almondtools.stringbench;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Benchmark)
public class Sample {

	private static final int SIZE = 256 * 256 * 1024;

	@Param({ "2", "4", "8", "16", "32", "64", "128", "256" })
	private int alphabetSize;
	@Param({ "2", "4", "8", "16", "32", "64", "128", "256" })
	private int patternSize;

	private String[] sample;
	private String[] pattern;
	private List<Integer>[] expected;

	public void setAlphabetSize(int alphabetSize) {
		this.alphabetSize = alphabetSize;
	}
	
	public void setPatternSize(int patternSize) {
		this.patternSize = patternSize;
	}
	
	@Setup
	public void setup() throws IOException {
		int sampleSize = alphabetSize * patternSize * 256;
		int sampleNumber = SIZE / sampleSize;

		Random random = new Random(13);

		this.sample = generateSample(random, alphabetSize, sampleSize, sampleNumber);
		this.pattern = generatePattern(random, sample, patternSize);
		this.expected = generateIndex(sample, pattern);

	}

	private String[] generateSample(Random random, int alphabetSize, int sampleSize, int sampleNumber) {
		String[] sample = new String[sampleNumber];
		for (int i = 0; i < sampleNumber; i++) {
			char[] sampleI = new char[sampleSize];
			for (int j = 0; j < sampleSize; j++) {
				sampleI[j] = (char) ('a' + random.nextInt(alphabetSize));
			}
			sample[i] = new String(sampleI);
		}
		return sample;
	}

	@SuppressWarnings("unchecked")
	private List<Integer>[] generateIndex(String[] sample, String[] pattern) {
		List<Integer>[] index = new List[sample.length];
		for (int i = 0; i < index.length; i++) {
			Pattern p = Pattern.compile(Pattern.quote(pattern[i]));
			Matcher m = p.matcher(sample[i]);
			index[i] = new ArrayList<Integer>();
			while (m.find()) {
				index[i].add(m.start());
			}
		}
		return index;
	}

	private String[] generatePattern(Random random, String[] sample, int patternSize) {
		String[] pattern = new String[sample.length];
		for (int i = 0; i < pattern.length; i++) {
			int index = random.nextInt(sample[i].length() - patternSize);
			pattern[i] = sample[i].substring(index, index + patternSize); 
		}
		return pattern;
	}

	public int patterns() {
		return pattern.length;
	}

	public String getSample(int i) {
		return sample[i];
	}

	public String[] getPattern() {
		return pattern;
	}

	@TearDown
	public synchronized void tearDown() {
		this.sample = null;
		this.pattern = null;
	}

	public void validate(int i, List<Integer> result) {
		if (result == null || !result.containsAll(expected[i])) {
			throw new IllegalStateException("expected " + expected[i] + ", but found " + result);
		}
	}

}
