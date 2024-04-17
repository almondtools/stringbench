package com.almondtools.stringbench.multipattern.incubation.neosearch;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;
import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmarkTest;

public class MultipleStringSearcherBenchmarkTest extends MultiPatternMatcherBenchmarkTest {

    @Override
    protected MultiPatternMatcherBenchmark getBenchmark() {
        return new MultipleStringSearcherBenchmark();
    }


}
