package com.almondtools.stringbench;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class StringBenchIncubation implements TestRule {

	private SinglePatternMatcherBenchmark sbenchmark;
	private MultiPatternMatcherBenchmark mbenchmark;

	public StringBenchIncubation(SinglePatternMatcherBenchmark benchmark) {
		this.sbenchmark = benchmark;
	}

	public StringBenchIncubation(MultiPatternMatcherBenchmark benchmark) {
		this.mbenchmark = benchmark;
	}

	@Override
	public Statement apply(Statement base, Description description) {
        return FailOnTimeout.builder()
            .withTimeout(10, SECONDS)
            .withLookingForStuckThread(true)
            .build(base);
	}

	public void benchmarkFindInString(SinglePatternSample sample) {
		sbenchmark.setup(sample);
		sbenchmark.benchmarkFindInString();
		sbenchmark.validate();
		sbenchmark.tearDown();
	}

	public void benchmarkFindInString(MultiPatternSample sample) {
		mbenchmark.setup(sample);
		mbenchmark.benchmarkFindInString();
		mbenchmark.validate();
		mbenchmark.tearDown();
	}

}
