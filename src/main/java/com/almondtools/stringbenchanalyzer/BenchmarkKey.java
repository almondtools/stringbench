package com.almondtools.stringbenchanalyzer;

import java.util.Objects;

public class BenchmarkKey {

	private String benchmarkName;
	private int patternNumber;

	public BenchmarkKey(BenchmarkRecord benchmark) {
		this.benchmarkName = benchmark.getBenchmarkName();
		this.patternNumber = benchmark.getNumber();
	}

	public String getBenchmarkName() {
		return benchmarkName;
	}

	public int getPatternNumber() {
		return patternNumber;
	}

	public String getTitle() {
		if (patternNumber <= 1) {
			return "Exact String Matching for single Patterns (" + benchmarkName + ")";
		} else {
			return "Exact String Matching for " + patternNumber + " Patterns (" + benchmarkName + ")";
		}
	}

	@Override
	public String toString() {
		return benchmarkName + "-" + patternNumber;
	}

	@Override
	public int hashCode() {
		return benchmarkName == null ? 0 : benchmarkName.hashCode() + patternNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BenchmarkKey that = (BenchmarkKey) obj;
		return Objects.equals(this.benchmarkName, that.benchmarkName)
			&& this.patternNumber == that.patternNumber;
	}

}
