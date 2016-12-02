package com.almondtools.stringbench.multipattern;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import com.almondtools.stringbench.ResultSizeNotAcceptedException;
import com.almondtools.stringbench.generator.GenerateSamples;

import net.amygdalum.util.text.CharUtils;

@State(Scope.Benchmark)
public class MultiPatternSample {

	@Param({ "2", "8", "32", "128" })
	private int patternNumber;
	@Param({ "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024" })
	private int alphabetSize;
	@Param({ "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024" })
	private int patternSize;

	private String sample;
	private File file;
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
		this.file = GenerateSamples.locateFile(sampleKey);
		this.patterns = GenerateSamples.readPatterns(patternKey);
		this.all = GenerateSamples.readAll(patternKey).getOrDefault(patternNumber, 0);
	}

	public String getSample() {
		return sample;
	}

	public File getFile() {
		return file;
	}

	public Set<String> getPattern() {
		return patterns.keySet().stream()
			.limit(patternNumber)
			.distinct()
			.collect(toSet());
	}

	@TearDown
	public synchronized void tearDown() {
		this.sample = null;
		this.patterns = null;
		this.all = 0;
	}

	public void validate(Set<String> pattern, List<Integer> result) {
		if (result == null) {
			throw new ResultSizeNotAcceptedException(pattern.stream()
				.map(str -> toReadableString(str))
				.collect(joining(",", "{", "}")), all, 0);
		}
		if (result.size() != all) {
			throw new ResultSizeNotAcceptedException(pattern.stream()
				.map(str -> toReadableString(str))
				.collect(joining(",", "{", "}")), all, result.size());
		}
	}

	private String toReadableString(String str) {
		return str.chars()
			.mapToObj(c -> CharUtils.charToString((char) c))
			.collect(joining());
	}

}
