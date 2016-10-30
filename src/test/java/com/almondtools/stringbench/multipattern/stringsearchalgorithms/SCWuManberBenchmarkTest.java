package com.almondtools.stringbench.multipattern.stringsearchalgorithms;

import org.junit.Test;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;
import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmarkTest;

public class SCWuManberBenchmarkTest extends MultiPatternMatcherBenchmarkTest {

	protected MultiPatternMatcherBenchmark getBenchmark() {
		return new SCWuManberBenchmark();
	}

	@Test
	public void testBenchmarkFindInFile_1024_1024_2() throws Exception {
		findInFileSample(1024, 1024, 2);
	}

}
