package com.almondtools.stringbench;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import com.almondtools.stringbenchgenerator.GeneratorOption;

@State(Scope.Benchmark)
public class SinglePatternSample {

	@Param({ "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024" })
	private int alphabetSize;
	@Param({ "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024" })
	private int patternSize;

	private String sample;
	private Map<String, List<Integer>> patterns;

	public void setAlphabetSize(int alphabetSize) {
		this.alphabetSize = alphabetSize;
	}

	public void setPatternSize(int patternSize) {
		this.patternSize = patternSize;
	}

	public boolean isValid() {
		return sample != null && patterns != null && !patterns.isEmpty();
	}

	@Override
	public String toString() {
		return "alphabet size : " + alphabetSize + ", pattern size : " + patternSize;
	}

	public String getKey() {
		return Texts.computeKey(alphabetSize, patternSize, new GeneratorOption[0]);
	}

	@Setup
	public void setup() throws IOException {
		Text text = Texts.TEXTS.text(getKey(),16);
		this.sample = text.sample;
		this.patterns = text.patterns;
	}

	public int patterns() {
		return patterns.size();
	}

	public String getSample() {
		return sample;
	}

	public Set<String> getPattern() {
		return patterns.keySet();
	}

	@TearDown
	public synchronized void tearDown() {
		this.sample = null;
		this.patterns = null;
	}

	public void validate(String pattern, List<Integer> result) {
		List<Integer> expected = patterns.get(pattern);
		if (result == null) {
			throw new ResultNotAcceptedException(expected, result);
		}
		if (!result.containsAll(expected)) {
			throw new ResultNotAcceptedException(expected, result);
		} else if (!expected.containsAll(result)) {
			throw new ResultNotAcceptedException(expected, result);
		}
	}

}
