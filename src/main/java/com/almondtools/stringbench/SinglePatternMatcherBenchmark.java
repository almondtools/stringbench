package com.almondtools.stringbench;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import com.almondtools.stringbenchanalyzer.Family;

@State(Scope.Thread)
public abstract class SinglePatternMatcherBenchmark {

	private SinglePatternSample sample;
	private Map<String, List<Integer>> results;

	public abstract void prepare(Set<String> pattern);

	public abstract List<Integer> find(String pattern, String text);

	public abstract String getId();

	public abstract Family getFamily();

	@Setup
	public void setup(SinglePatternSample sample) {
		if (!sample.isValid()) {
			throw new SampleNotQualifiedException();
		}
		this.sample = sample;
		prepare(sample.getPattern());
		this.results = new HashMap<>();
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5)
	@Measurement(iterations = 5)
	@Fork(1)
	public void benchmarkFind() {
		for (String pattern : sample.getPattern()) {
			List<Integer> result = find(pattern, sample.getSample());
			results.put(pattern, result);
		}
	}

	@TearDown
	public void tearDown() {
		for (String pattern : sample.getPattern()) {
			List<Integer> result = results.get(pattern);
			sample.validate(pattern, result);
		}
		sample = null;
	}
	
}
