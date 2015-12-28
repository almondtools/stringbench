package com.almondtools.stringbench;

import static com.almondtools.stringsandchars.search.MatchOption.LONGEST_MATCH;
import static com.almondtools.stringsandchars.search.MatchOption.NO_OVERLAP;
import static java.util.Arrays.asList;

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
import com.almondtools.stringsandchars.search.AhoCorasick;
import com.almondtools.stringsandchars.search.StringFinder;
import com.almondtools.stringsandchars.search.StringMatch;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

@State(Scope.Benchmark)
public class MultiPatternSample {

	private static final int MAX_SAMPLE_SIZE = 1024 * 1024;

	@Param({ "2", "8", "32", "128" })
	private int patternNumber;
	@Param({ "2", "4", "8", "16", "32", "64", "128", "256" })
	private int alphabetSize;
	@Param({ "2", "4", "8", "16", "32", "64", "128", "256" })
	private int patternSize;

	private String sample;
	private String[] pattern;
	private List<Integer> expected;

	public void setPatternNumber(int patternNumber) {
		this.patternNumber = patternNumber;
	}

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
		return "pattern number : " + patternNumber + ", alphabet size : " + alphabetSize + ", pattern size : "
				+ patternSize;
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

	public String[] generatePatterns(Random random, String sample) {
		if (isNotSelective()) {
			return new String[0];
		} else {
			return generateRandomPatterns(random, sample);
		}
	}

	private boolean isNotSelective() {
		int result = 1;
		for (int i = 0; i < patternSize && result <= patternNumber; i++) {
			result *= alphabetSize;
		}
		return result <= patternNumber;
	}

	private String[] generateRandomPatterns(Random random, String sample) {
		String[] pattern = new String[patternNumber];
		int randomRange = sample.length() - patternSize;
		for (int i = 0; i < patternNumber; i++) {
			int index = random.nextInt(randomRange);
			int size = patternSize / 2 + 1 + random.nextInt(patternSize / 2);
			pattern[i] = sample.substring(index, index + size);
			if (i % 2 == 1) {
				pattern[i] = mutate(random, pattern[i]);
			}
		}
		return pattern;
	}

	private String mutate(Random random, String string) {
		int len = string.length();
		int posToMutate = len / 2 + random.nextInt(len / 2);
		int posToMutateTo = random.nextInt(len);
		StringBuilder buffer = new StringBuilder(string);
		buffer.setCharAt(posToMutate, buffer.charAt(posToMutateTo));
		return buffer.toString();
	}

	private List<Integer> generateIndex(String sample, String[] pattern) {
		StringSearchAlgorithm a = new AhoCorasick(asList(pattern));
		StringFinder finder = a.createFinder(new StringCharProvider(sample, 0), LONGEST_MATCH, NO_OVERLAP);
		List<Integer> index = new ArrayList<>();
		for (StringMatch match : finder.findAll()) {
			index.add((int) match.start());
		}
		return index;
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

	public void validate(List<Integer> result) {
		if (result == null || !result.containsAll(expected)) {
			throw new ResultNotAcceptedException(expected, result);
		}
	}

}
