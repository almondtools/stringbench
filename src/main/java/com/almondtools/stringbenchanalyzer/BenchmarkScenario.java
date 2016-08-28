package com.almondtools.stringbenchanalyzer;

public class BenchmarkScenario {

	private int alphabet;
	private int patternSize;

	public BenchmarkScenario(BenchmarkRecord benchmark) {
			this.alphabet = benchmark.getAlphabet();
			this.patternSize = benchmark.getPatternSize();
		}

	@Override
	public int hashCode() {
		return alphabet * 17 + patternSize * 7;
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
		BenchmarkScenario that = (BenchmarkScenario) obj;
		return this.alphabet == that.alphabet
			&& this.patternSize == that.patternSize;
	}

}