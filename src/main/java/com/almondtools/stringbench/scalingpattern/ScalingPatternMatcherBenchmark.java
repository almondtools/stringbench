package com.almondtools.stringbench.scalingpattern;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import com.almondtools.stringbench.SampleNotQualifiedException;

@State(Scope.Thread)
public abstract class ScalingPatternMatcherBenchmark {

	private ScalingPatternSample sample;
	private List<Integer> result;

	public abstract void preparePatterns(Set<String> patterns);

	public abstract List<Integer> find(String text);
	public abstract List<Integer> find(File file) throws IOException;
	public abstract void free();

	public abstract String getId();

	@Setup
	public void setup(ScalingPatternSample sample) {
		if (!sample.isValid()) {
			throw new SampleNotQualifiedException();
		}
		this.sample = sample;
		preparePatterns(sample.getPattern());
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5, time=1)
	@Measurement(iterations = 5, time=1)
	@Fork(1)
	public void benchmarkFindInString() {
		result = find(sample.getSample());
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5, time=1)
	@Measurement(iterations = 5, time=1)
	@Fork(1)
	public void benchmarkFindInFile() throws IOException {
		result = find(sample.getFile());
	}
	
	@TearDown(Level.Iteration)
	public void validate() {
		sample.validate(sample.getPattern(), result);
	}

	@TearDown
	public void tearDown() {
		sample = null;
		result.clear();
		free();
	}

}
