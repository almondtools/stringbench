package com.almondtools.stringbench.scalingpattern;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import com.almondtools.stringbench.ResultSizeNotAcceptedException;
import com.almondtools.stringbench.generator.GenerateSamples;

import net.amygdalum.util.text.CharUtils;

@State(Scope.Benchmark)
public class ScalingPatternSample {

	@Param({ "ecoli","human-protein", "kjb" })
	private String patternCorpus;
	@Param({ "459", "1250","2500","5000","10000","20000" })
	private int patternNumber;

	private String sample;
	private File file;
	private Map<String, Integer> patterns;
	private int all;

	public void setPatternCorpus(String patternCorpus) {
		this.patternCorpus = patternCorpus;
	}
	
	public void setPatternNumber(int patternNumber) {
		this.patternNumber = patternNumber;
	}

	public boolean isValid() {
		return sample != null && patterns != null && !patterns.isEmpty();
	}

	@Override
	public String toString() {
		return "pattern number : " + patternNumber + ", corpus : "
			+ patternCorpus;
	}

	@Setup
	public void setup() throws IOException {
		String sampleKey = "sample-" + patternCorpus;
		String patternKey = "pattern-" + patternCorpus;
		this.sample = GenerateSamples.readSample(sampleKey);
		this.file = GenerateSamples.locateFile(sampleKey);
		this.patterns = GenerateSamples.readPatterns(patternKey);
		this.all = patterns.entrySet().stream()
			.limit(patternNumber)
			.collect(Collectors.summingInt(entry -> entry.getValue()));
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
