package com.almondtools.stringbenchanalyzer;

import static java.util.stream.Collectors.joining;

import java.util.stream.Stream;

public class BenchmarkRecord implements Comparable<BenchmarkRecord> {

	private int patternNumber;
	private int alphabet;
	private int patternSize;
	private double time;
	private String name;
	private Family family;

	public BenchmarkRecord(int patternNumber, int alphabet, int patternSize, double time, String name, Family family) {
		this.patternNumber = patternNumber;
		this.alphabet = alphabet;
		this.patternSize = patternSize;
		this.time = time;
		this.name = name;
		this.family = family;
	}

	public int getNumber() {
		return patternNumber;
	}

	public int getAlphabet() {
		return alphabet;
	}

	public int getPatternSize() {
		return patternSize;
	}

	public String getKey() {
		return alphabet + "-" + patternSize;
	}

	public String getName() {
		return name;
	}
	
	public Family getFamily() {
		return family;
	}

	public String getNameAndFamily() {
		return name + ':' + family;
	}

	@Override
	public int compareTo(BenchmarkRecord o) {
		return Double.compare(this.time, o.time);
	}

	public String coordinates() {
		return Stream.of(name, family, alphabet, patternSize)
			.map(value -> value.toString())
			.collect(joining(";"));
	}

	@Override
	public String toString() {
		return name + ":" + time;
	}
}
