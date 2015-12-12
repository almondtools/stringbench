package com.almondtools.stringbench;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import com.almondtools.stringsandchars.io.StringCharProvider;
import com.almondtools.stringsandchars.search.KnuthMorrisPratt;
import com.almondtools.stringsandchars.search.StringFinder;
import com.almondtools.stringsandchars.search.StringMatch;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

@State(Scope.Benchmark)
public class SinglePatternSample {

	private static final int MAX_SAMPLE_SIZE = 1024 * 1024;
	private static final int MAX_PATTERNS = 64;

	@Param({ "2", "4", "8", "16", "32", "64", "128", "256" })
	private int alphabetSize;
	@Param({ "2", "4", "8", "16", "32", "64", "128", "256" })
	private int patternSize;

	private String sample;
	private String[] pattern;
	private List<Integer>[] expected;

	public void setAlphabetSize(int alphabetSize) {
		this.alphabetSize = alphabetSize;
	}

	public void setPatternSize(int patternSize) {
		this.patternSize = patternSize;
	}

	public boolean isValid() {
		return pattern != null && pattern.length > 0;
	}

	@Override
	public String toString() {
		return "alphabet size : " + alphabetSize + ", pattern size : " + patternSize;
	}

	@Setup
	public void setup() throws IOException {
		Random random = new Random(13);
		int sampleSize = generateSampleSize();

		this.sample = generateSample(random, sampleSize);
		this.pattern = generatePatterns(random, sample);
		this.expected = generateIndex(sample, pattern);
	}

	private int generateSampleSize() {
		int result = 256;
		for (int i = 0; i < patternSize && result < MAX_SAMPLE_SIZE; i++) {
			result *= alphabetSize;
		}
		return result;
	}

	private String generateSample(Random random, int sampleSize) {
		char[] sample = new char[sampleSize];
		for (int i = 0; i < sampleSize; i++) {
			sample[i] = getChar(random.nextInt(alphabetSize));
		}
		return new String(sample);
	}

	private char getChar(int index) {
		return (char) ('a' + index);
	}

	public String[] generatePatterns(Random random,String sample){
		if (isNotSelective()) {
			return generateAllPatterns(patternSize * alphabetSize);
		} else {
			return generateRandomPatterns(random, sample);
		}
	}

	private boolean isNotSelective() {
		int result = 1;
		for (int i = 0; i < patternSize && result <= MAX_PATTERNS; i++) {
			result *= alphabetSize;
		}
		return result <= MAX_PATTERNS;
	}

	private String[] generateAllPatterns(int number) {
		String[] pattern = new String[number];
		for (int i = 0; i < pattern.length; i++) {
			pattern[i] = createPattern(i);
		}
		return pattern;
	}
	
	private String createPattern(int i) {
		char[] pattern = new char[patternSize];
		for (int j = 0; j < pattern.length; j++) {
			int r = i % alphabetSize;
			pattern[j] = getChar(r);
			i = i / alphabetSize;
		}
		return new String(pattern);
	}

	private String[] generateRandomPatterns(Random random, String sample) {
		String[] pattern = new String[MAX_PATTERNS];
		int randomRange = sample.length() - patternSize;
		for (int i = 0; i < MAX_PATTERNS; i++) {
			int index = random.nextInt(randomRange);
			pattern[i] = sample.substring(index, index + patternSize);
		}
		return pattern;
	}

	@SuppressWarnings("unchecked")
	private List<Integer>[] generateIndex(String sample, String[] pattern) {
		List<Integer>[] index = new List[pattern.length];
		for (int i = 0; i < pattern.length; i++) {
			StringSearchAlgorithm a = new KnuthMorrisPratt(pattern[i]);
			StringFinder finder = a.createFinder(new StringCharProvider(sample, 0));
			index[i] = new ArrayList<Integer>();
			for (StringMatch match : finder.findAllNonOverlapping()) {
				index[i].add((int) match.start());
			}
		}
		return index;
	}

	public int patterns() {
		return pattern.length;
	}

	public String getSample() {
		return sample;
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
			throw new ResultNotAcceptedException(expected[i], result);
		}
	}

}
