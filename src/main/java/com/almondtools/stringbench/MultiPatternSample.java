package com.almondtools.stringbench;

import static java.util.stream.Collectors.toSet;

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
public class MultiPatternSample {

	@Param({ "2", "8", "32", "128" })
	private int patternNumber;
	@Param({ "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024" })
	private int alphabetSize;
	@Param({ "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024" })
	private int patternSize;

	private String sample;
	private Map<String, Integer> patterns;
	private int all;

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
		return sample != null && patterns != null && !patterns.isEmpty();
	}

	@Override
	public String toString() {
		return "pattern number : " + patternNumber + ", alphabet size : " + alphabetSize + ", pattern size : "
			+ patternSize;
	}

	@Setup
	public void setup() throws IOException {
		String sampleKey = GenerateSamples.computeKey(alphabetSize); 
		String patternKey = GenerateSamples.computeKey(alphabetSize, patternSize); 
		this.sample = GenerateSamples.readSample(sampleKey);
		this.patterns = GenerateSamples.readPatterns(patternKey);
		this.all = GenerateSamples.readAll(patternKey).getOrDefault(patternNumber, 0);
	}

	public String getSample() {
		return sample;
	}

	public Set<String> getPattern() {
		return patterns.keySet().stream()
			.limit(patternNumber)
			.collect(toSet());
	}

	@TearDown
	public synchronized void tearDown() {
		this.sample = null;
		this.patterns = null;
		this.all = 0;
	}

	public void validate(List<Integer> result) {
		if (result == null) {
			throw new ResultSizeNotAcceptedException(all, 0);
		}
		if (result.size() != all) {
			throw new ResultSizeNotAcceptedException(all, result.size());
		}
	}

}
