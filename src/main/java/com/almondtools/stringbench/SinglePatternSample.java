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

import com.almondtools.stringbenchgenerator.GenerateSamples;

@State(Scope.Benchmark)
public class SinglePatternSample {

	@Param({ "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024" })
	private int alphabetSize;
	@Param({ "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024" })
	private int patternSize;

	private String sample;
	private Map<String, Integer> patterns;

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

	@Setup
	public void setup() throws IOException {
		String sampleKey = GenerateSamples.computeKey(alphabetSize);
		String patternKey = GenerateSamples.computeKey(alphabetSize, patternSize);
		this.sample = GenerateSamples.readSample(sampleKey);
		this.patterns = GenerateSamples.readPatterns(patternKey);
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
		Integer expected = patterns.get(pattern);
		if (result == null) {
			throw new ResultSizeNotAcceptedException(expected, 0);
		}
		if (result.size() != expected) {
			throw new ResultSizeNotAcceptedException(expected, result.size());
		}
	}

}
