package com.almondtools.stringbench;

import java.util.List;
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

@State(Scope.Thread)
public abstract class MultiPatternMatcherBenchmark {

	private MultiPatternSample sample;

	public abstract void prepare(String[] pattern);

	public abstract List<Integer> find(int i, String text);

	public abstract String getId();

	@Setup
	public void setup(MultiPatternSample sample) {
		this.sample = sample;
		prepare(sample.getPattern());
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5)
	@Measurement(iterations = 5)
	@Fork(1)
	public void benchmarkPrepare() {
		prepare(sample.getPattern());
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5)
	@Measurement(iterations = 5)
	@Fork(1)
	public void benchmarkFind() {
		for (int i = 0; i < sample.patterns(); i++) {
			List<Integer> result = find(i, sample.getSample(i));
			sample.validate(i, result);
		}
	}

	@TearDown
	public void tearDown() {
		this.sample = null;
	}

}
